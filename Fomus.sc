
Fomus {

	var <fNoteList;
	var <>fileName = "~/scwork/fomusTest".standardizePath;
	var <>pdfViewer = "okular";
	var <>header = "";

	*new { arg noteList;
		^super.new.init(noteList);
	}

	init { arg noteList=[];

		fNoteList = Array.new;

		if( noteList.class == Array,
			{
				noteList.do({ arg i;
					if(	i.class == Event,
						{ fNoteList = fNoteList.add(i) },
						{ "All elements must be Events".warn }
					);
				})
			},{
				if(	noteList == Event,
					{ fNoteList = fNoteList.add(noteList) },
					{ "This must be a Event".warn }
				);
			}
		);
	}

	put { arg stuffIn, n=1;

		case		
		{stuffIn.class == Event}
		{ fNoteList = fNoteList.add(stuffIn)}

		{stuffIn.class == Array}
		{
			stuffIn.do({ arg thisEvent;
				if( thisEvent.class == Event,
					{ fNoteList = fNoteList.add(thisEvent)},
					{ "At least one element of the Array is not an Event".warn}
				);
			})
		}

		{stuffIn.class == Routine}
		{
			stuffIn.nextN(n,()).do({ arg thisEvent;
				fNoteList = fNoteList.add(thisEvent)
			});
		}
		
		{(stuffIn.class == Event || stuffIn.class == Array).not}
		{ "You must provide an Event, a Stream or an Array of Events".warn};
		
	}

	allStrings {
		
		var out = "";
		fNoteList.do({ arg i; out = out ++ i.asFomusString ++ "\n" });
		^out;
	}


	write {
		var file;
		file = File(this.fileName ++ ".fms","w");
		file.write(this.header);
		file.write(this.allStrings);
		file.close;
	}

	make {
		(
			"fomus " ++ this.fileName ++ ".fms" ++
			" -o " ++ this.fileName ++ ".ly"
		).unixCmd;
	}

	show {
		(this.pdfViewer ++ " " ++ this.fileName ++ ".pdf").unixCmd;
	}

	plot {
		fork {
			this.write;
			this.make;
			this.show;
		}
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
