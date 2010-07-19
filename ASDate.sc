// First attempt to write a Date-Time class.
// This does not take into account any time zone specification
// 
// Supports only the following format 
// ASDate.new("yyyy-MM-dd hh:mm:ss")
//
//
//
//
// var name = "ahmet", surname = "kizilay";
// var email = name + "." + surname + "@" + "gmail.com"

ASDate {
	var <year, <month, <day, <hour, <minute, <second, millis = nil;
	classvar daysInMonth = #[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

	*new {
		arg timestr;
		^super.new.pr_init(timestr);
	}

	pr_init {
		arg timestr;
		var datepart, timepart;
		// ("timestr: " ++ timestr).postln;
		
		timestr.split($ ).do {
			arg val, num;
			// [num, val].postln;
			switch(num, 
				0, { this.pr_parseDatePart(val); },
				1, { this.pr_parseTimePart(val); }
			);
		}
	}
	
	pr_parseDatePart {
		arg datePart;
		// ("date: " ++ datePart).postln;
		datePart.split($-).do {
			arg val, num;
			// [num, val].postln;
			switch(num, 
				0, { year = val.asInt; },
				1, { month = val.asInt; },
				2, { day = val.asInt; }
			);
		}
	}

	pr_parseTimePart {
		arg timePart;
		// ("time: " ++ timePart).postln;
		timePart.split($:).do {
			arg val, num;
			switch(num, 
				0, { hour = val.asInt; },
				1, { minute = val.asInt; },
				2, { second = val.asInt; }
			);
		}
	}

	// epoch is January 1st, 1970 00:00:00
	// deal with GMT later
	pr_calculateMillis {
		var yearCont, dayCont = 0, hourCont, minCont, secCont, isLeap;
		yearCont = (year - 1970) * 31536000000.0;
		//"year ".post; yearCont.postln;
		dayCont = (this.calculateDaysPassed(month - 1) + (day - 1));
		//"day ".post; dayCont.postln;
		dayCont = (dayCont + this.howManyLeapUntilThisYear() + if(this.isPastLeapDay(), 1, 0))* 86400000.0;
		//"day2 ".post; dayCont.postln;
		hourCont = hour * 3600000.0;
		//"hour ".post; hourCont.postln;
		minCont = minute * 60000.0;
		//"min ".post; minCont.postln;
		secCont = second * 1000.0;
		//"sec ".post; secCont.postln;
		
		^yearCont + dayCont + hourCont + minCont + secCont;
	}

	howManyLeapUntilThisYear {
		var divByFour, divByHundred, divByFourHundred;
		divByFour = max(ceil((year - 1972) / 4), 0);
		divByHundred = max(ceil((year - 1972) / 100), 0);
		divByFourHundred = max(ceil((year - 1972) / 400), 0);
		^ divByFour - divByHundred + divByFourHundred;
	}

	isLeapYear {
		^if(year % 4 != 0, false, {
			if(year % 100 != 0, true, {
				if(year % 400 == 0, true, false);
			});
		});
	}

	isPastLeapDay {
		^if(this.isLeapYear(),{
			if(month > 2, true, false);
		}, false);
	}

	calculateDaysPassed {
		arg monthPassed;
		var sum = 0;
		(monthPassed).do {
			arg i;
			sum = sum + daysInMonth.at(i);
		}
		^sum;
	}

	millis {
		if(millis == nil, {
			millis =  this.pr_calculateMillis();
		});
		^millis;
	}
}