// A Collection of helper methods I find myself in need of
// 
// Note that I use firefox as the default browser and
// Emacs as the default SuperCollider Editor.
// and so should you...
//
//
//
//
// var name = "ahmet", surname = "kizilay";
// var email = name + "." + surname + "@" + "gmail.com"

ASUtils {
	
	*openHelpInBrowser {
		arg className;
		("firefox -new-tab \"" ++ (className.name ++ ".helpFilePath").interpret ++ "\"").unixCmd;
		^"ok";
	}

	*openUrlInBrowser {
		arg url;
		("firefox -new-tab \"" ++ url + "\"").unixCmd;
		^"ok";
	}

	*openHelpInBrowser40 {
		arg className;
		("firefox-4.0 -new-tab \"" ++ (className.name ++ ".helpFilePath").interpret ++ "\"").unixCmd;
		^"ok";
	}

	*openUrlInBrowser40 {
		arg url;
		("firefox-4.0 -new-tab \"" ++ url + "\"").unixCmd;
		^"ok";
	}

	*searchInHelp {
		arg queryString;
		var resultList, outputString = "", newBuffer;
		resultList = Help.search(queryString, true);
		outputString = "Found" + resultList.size + "results\n\n";
		resultList.do {
			arg item;
			outputString = outputString ++ ("\tdocname: " ++ item.docname ++ "\n");
			outputString = outputString ++ ("\tpath: " ++ item.path ++ "\n");
			outputString = outputString ++ "\n";
		};

		newBuffer = EmacsBuffer.new("HELP::Results");
		newBuffer.insert(outputString);
		newBuffer.front;
		^"ok";
	}

	*askGoogle {
		arg searchString;
		var splitted, url;
		url = "http://www.google.com/search?ie=utf-8&oe=utf-8&q=";
		splitted = searchString.split($ );
		splitted.do {
			arg val;
			url = url ++ val ++ "+";
		};
		url = url.copyFromStart(url.size - 2);
		("firefox -new-tab \"" ++ url ++ "\"").unixCmd;
		^"ok";		
	}

	*askGoogle40 {
		arg searchString;
		var splitted, url;
		url = "http://www.google.com/search?ie=utf-8&oe=utf-8&q=";
		splitted = searchString.split($ );
		splitted.do {
			arg val;
			url = url ++ val ++ "+";
		};
		url = url.copyFromStart(url.size - 2);
		("firefox-4.0 -new-tab \"" ++ url ++ "\"").unixCmd;
		^"ok";		
	}
}
