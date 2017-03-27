Fomus {
	var <eventList;
	var <>fileName = "~/Desktop/SCFomus_";
	var <>lilyPath;
	var <>lilyViewPath;
	var <>timeTag;
	var <>qt = true;

	*new { arg noteList, n;
		^super.new.init(noteList, n);
	}

	init { arg that=[], n=1;

		Platform.case(
			\osx, {
				lilyPath = "/Applications/LilyPond.app/Contents/Resources/bin/lilypond ";
				lilyViewPath = "open ";
				"Please check LilyPond and PDF viewer paths.".warn
			},
			\linux, {
				lilyPath = "lilypond ";
				lilyViewPath = "xpdf ";
				"Please check LilyPond and PDF viewer paths.".warn
			},
			\windows, {
				"Please set LilyPond and PDF viewer paths.".warn
			}
		);

		eventList = Array.new;
		this.newTag;

		(that.size > 0 or: that.class == Routine).if({
			this.put(that, n)
		});

	}

    put { arg that, n=1; this.add(that, n)}

	add { arg that, n=1;

		case
		{that.class == Event}
		{ eventList = eventList.add(that)}

		{that.class == Array}
		{
			that.do { |thisEvent|
				if( thisEvent.class == Event,
					{ eventList = eventList.add(thisEvent)},
					{ "At least one element of the Array is not an Event".error}
				);
			}
		}

		{that.class == Routine}
		{
			that.nextN(n,()).do { |thisEvent|
				eventList = eventList.add(thisEvent)
			};
		}

		{(that.class == Event or: that.class == Array).not}
		{ "You must provide an Event, a Stream or an Array of Events".error};

	}

	asString {
		var out = String.new;
		eventList.do { arg i;
			out = out ++ i.asFomusString ++ "\n"
		};
		^out
	}

	qtString {
		^this.qt.if(
			{"quartertones = yes"},
			{"quartertones = no"})
	}

	header {
		^(
			this.qtString ++ "\n"
			++ "lily-exe-path = " ++ this.lilyPath ++ "\n"
			++ "lily-view-exe-path = " ++ this.lilyViewPath  ++ "\n"
			++ "part <id apart, inst piano>"  ++ "\n"
			++ "part apart"  ++ "\n" ++ "voice (1 2 3)"  ++ "\n"
		).asString
	}

	newTag { timeTag = Date.getDate.stamp.asString }

	write {
        var path, f;
        path = this.fileName.standardizePath ++ timeTag ++ ".fms";
		f = File.open(path,"w");
		f << this.header << "\n";
		f << this.asString;
		f.close
	}

	ly {
		var f;
        this.newTag;
        this.write;
        f = this.fileName.standardizePath ++ timeTag;
        format("fomus %.fms -o %.ly", f, f).runInTerminal;
	}

	midi {
        var f;
        this.newTag;
        this.write;
        f = this.fileName.standardizePath ++ timeTag;
        format("fomus %.fms -o %.mid", f, f).runInTerminal;
	}

	xml {
		var f;
        this.newTag;
        this.write;
        f = this.fileName.standardizePath ++ timeTag;
        format("fomus %.fms -o %.xml", f, f).runInTerminal;
	}

	show {
        format("% %.pdf", this.lilyViewPath, this.fileName.standardizePath).unixCmd
    }
}
