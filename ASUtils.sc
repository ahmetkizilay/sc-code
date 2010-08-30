// A Collection of helper methods I find myself in need of
// 
// 
// 
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

	*searchInHelp {
		arg queryString;
		var resultList, outputString = "", newBuffer;
		resultList = Help.search(queryString, true);
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
}
