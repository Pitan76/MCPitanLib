<?php
$info_properties = file_get_contents('../info.properties');
preg_match('/mod_version=(.*)/', $info_properties, $matches);
$matches[1] = str_replace(["\r", "\n"], '', $matches[1]);

define('VERSION', $matches[1]);

define('DIRS', array(
	'common' => 'common/build/',
	'fabric' => 'fabric/build/',
	'forge' => 'forge/build/',
	'neoforge' => 'neoforge/build/',
));

define('GAME_VERSIONS', array(
	'1.16.5' => '1.16.5',
	'1.18.2' => '1.18.2',
	'1.19.2' => '1.19.2',
	'1.20.1' => '1.20.1',
	'1.20.4' => '1.20.4',
));

define('PLATFORM_FILE_MARK', array(
	'common' => '',
	'fabric' => '-fabric',
	'forge' => '-forge',
	'neoforge' => '-neoforge',
));

foreach (DIRS as $type => $dir) {
	
	foreach (GAME_VERSIONS as $file_ver => $ver) {
		
		$postData = array();
		
		$postData['group_id'] = 'net.pitan76';
		$postData['artifact_id'] = 'mcpitanlib-' . $type . '+' . $ver;
		$postData['version'] = VERSION;

		$files = array(
			$dir . 'libs/mcpitanlib-' . VERSION . '-' . $file_ver . PLATFORM_FILE_MARK[$type] . '.jar',
			$dir . 'libs/mcpitanlib-' . VERSION . '-' . $file_ver . PLATFORM_FILE_MARK[$type] . '-sources.jar',
			$dir . 'publications/maven' .  ucfirst($type) . '/mcpitanlib-' . $file_ver . '.pom',
		);

		$pom = '../' . $dir . 'publications/maven' .  ucfirst($type) . '/mcpitanlib-' . $file_ver . '.pom';

		$pom_str = file_get_contents($pom);
		$pom_str = preg_replace('/' . preg_quote(VERSION . '-' . $file_ver . PLATFORM_FILE_MARK[$type] . '</version>', '/'). '/', VERSION . '</version>', $pom_str, 1);
		$pom_str = preg_replace('/' . preg_quote('mcpitanlib' . PLATFORM_FILE_MARK[$type]  . '</artifactId>', '/') . '/', 'mcpitanlib' .  ($type == "common" ? '-common' : PLATFORM_FILE_MARK[$type]) . '+' . $file_ver . '</artifactId>', $pom_str, 1);

		file_put_contents($pom, $pom_str);

		$notfound = false;

		foreach ($files as $index => $file) {

			if (!file_exists(realpath("../" . $file))) {
				echo "not found" . $file;
				$notfound = true;
				continue;
			}
			$postData['upload[' . $index . ']'] = curl_file_create(
				realpath("../" . $file),
				mime_content_type("../" . $file),
				basename("../" . $file)
			);
			echo "Uploading '" . $file . "'\n";
		}

		if ($notfound) continue;
		
		$request = curl_init('http://localhost/maven/maven.php');
		curl_setopt($request, CURLOPT_POST, true);
		curl_setopt($request, CURLOPT_POSTFIELDS, $postData);
		curl_setopt($request, CURLOPT_RETURNTRANSFER, true);
		$result = curl_exec($request);
		
		if ($result === false) {
			error_log(curl_error($request));
		}
		curl_close($request);
	}
}
