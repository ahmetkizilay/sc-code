// This is a utilitiy class that makes it easier to assign
// control to synths.
//  
// Accepts a reference to a synth, 
// a function that maps midi values to the synth parameter,
// the parameter of the synth the midi is mapped on to.
// 
// upon creating an instance use midiLearn to setup the midi.
//
// var name = "ahmet", surname = "kizilay";
// var email = name + "." + surname + "@" + "gmail.com"


ASMidiSynthControl {
	var <synth, midiControl, busControl, function, controlParam, <>isActive;
	*new {
		arg a_synth, a_function, a_controlParam;
		^super.new.pr_init(a_synth, a_function, a_controlParam);
	}

	pr_init {
		arg a_synth, a_function, a_controlParam;
		synth = a_synth;
		function = a_function;
		controlParam = a_controlParam;
		isActive = false;
		
		busControl = Bus.control(Server.default, 1);
		synth.map(controlParam, busControl);
		
		midiControl = CCResponder({
			arg src, chan, num, val;
			(isActive).if({
				busControl.value = function.value(val);
			});
		});
	}
	
	free {
		busControl.free; midiControl.remove;
		^super.free;
	}

	midiLearn {
		midiControl.learn;
	}
}