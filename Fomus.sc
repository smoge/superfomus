
Fomus {
	var <eventList;
 	var <>fileName = "~/Desktop/SuperFomus";
	var <>pdfViewer = "okular";
	var <>qt = true;
	var <>lilyPath = "/usr/bin/lilypond";
	var <>lilyViewPath = "/usr/bin/okular";
	
	*new { arg noteList, n;
		^super.new.init(noteList, n);
	}

	init { arg thisStuff=[], n=1;

		eventList = Array.new;

		(thisStuff.size > 0 || thisStuff.class == Routine).if({
			this.put(thisStuff, n)
		});
		
	}

	put { arg stuffIn, n=1;

		case		
		{stuffIn.class == Event}
		{ eventList = eventList.add(stuffIn)}

		{stuffIn.class == Array}
		{
			stuffIn.do({ arg thisEvent;
				if( thisEvent.class == Event,
					{ eventList = eventList.add(thisEvent)},
					{ "At least one element of the Array is not an Event".error}
				);
			})
		}

		{stuffIn.class == Routine}
		{
			stuffIn.nextN(n,()).do({ arg thisEvent;
				eventList = eventList.add(thisEvent)
			});
		}
		
		{(stuffIn.class == Event || stuffIn.class == Array).not}
		{ "You must provide an Event, a Stream or an Array of Events".error};
		
	}

	asString {
		
		var out = "";
			eventList.do({ arg i; out = out ++ i.asFomusString ++ "\n" });
		^out;
	}

	qtString {
		this.qt.if(
			{^"quartertones = yes"},
			{^"quartertones = no"})
	}
	
	header {

		^(
				this.qtString ++ "\n" ++
				"lily-exe-path = " ++ this.lilyPath ++ "\n" ++
				"lily-view-exe-path = " ++ this.lilyViewPath  ++ "\n"
		).asString;
	}

	write {
		var file;
		file = File(this.fileName.standardizePath ++ ".fms","w");
		file.write(this.header ++ "\n");
		file.write(this.asString);
		file.close;
	}

	makeLy {
		this.write;		
		(
			"fomus " ++ this.fileName.standardizePath ++ ".fms" ++
			" -o " ++ this.fileName.standardizePath ++ ".ly"
		).unixCmd;
	}

	makeMidi {
		this.write;
		
		(
			"fomus " ++ this.fileName.standardizePath ++ ".fms" ++
			" -o " ++ this.fileName.standardizePath ++ ".mid"
		).unixCmd;
	}


	makeXml {
		this.write;
		(
			"fomus " ++ this.fileName.standardizePath ++ ".fms" ++
			" -o " ++ this.fileName.standardizePath ++ ".mid"
		).unixCmd;
	}


	show {
		(this.pdfViewer ++ " " ++ this.fileName.standardizePath ++ ".pdf").unixCmd;
	}

}

+ Event {

	asFomusString {
		var outString = "";

		this.keys.includes(\instrument).if({
			outString = "part " ++ this[\instrument].asString
		});

		this.keys.includes(\part).if({
			outString = "part " ++ this[\part].asString
		});

		this.keys.includes(\voice).if({
			outString = "voice " ++ this[\voice].asString
		});

		this.keys.includes(\time).if({
			outString = outString ++ " time " ++ this[\time].asString
		});

		this.keys.includes(\time).not.if({
			outString = outString ++ " time + "
		});

		this.keys.includes(\dur).or(this.keys.includes(\duration)).if({
			outString = outString ++ " duration " ++ this[\dur].asString
		});

		this.keys.includes(\midinote).if({
			outString = outString ++ " pitch " ++ this[\midinote].asString
		});

		this.keys.includes(\note).if({
			outString = outString ++ " pitch " ++ (this[\note] + 60).asString
		});

		this.keys.includes(\pitch).if({
			outString = outString ++ " pitch " ++ (this[\pitch] + 60).asString
		});

		outString = outString ++ ";";

		^outString
	}
}
