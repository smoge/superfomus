
Fomus {

	var <fNoteList;
	var <>fileName = "/home/smoge/scwork/fomusTest";
	var <>header = "";

	*new { arg noteList;
		^super.new.init(noteList);
	}

	init { arg noteList;

		fNoteList = Array.new;

		if( noteList.class.isArray,
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
		("okular " ++ this.fileName ++ ".pdf").unixCmd;
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

/*

a = (voice: 1, off: 0, dur:1, pitch:2)
a.class
a.asFomusString.postln

a[\pitch].class

a = Fomus.new(
	[ 	(voice: 1, off: 0, dur:0.2342, pitch:2),
	(voice: 1, off: 0, dur:1.23498756, pitch:3),
	(voice: 1, off: 0, dur:3.87634521876, pitch:1)]
	)
	
	a.write
	a.make
	a.show

*/
