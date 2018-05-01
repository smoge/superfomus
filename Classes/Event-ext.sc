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

		this.isRest.not.if({
			this.keys.includes(\midinote).if({

				if( this[\midinote].class == Array,
					{
						this[\midinote].do({arg thisNote;
							outString = outString ++ " pitch " ++ thisNote.asString ++ "; \n"
						})
					},{
						outString = outString ++ " pitch " ++ (this[\midinote]).asString;
						outString = outString ++ ";";
					}
				)
			})
		});

		this.keys.includes(\note).if({

			if( this[\note].class == Array,
				{ // if this is a chord:
					this[\note].do({arg thisNote;
						outString = outString ++ " pitch " ++ (thisNote + 60).asString + "; \n"
					})
				},{
					outString = outString ++ " pitch " ++ (this[\note] + 60).asString;
					outString = outString ++ ";";
				}
			)
		});
		^outString
	}
}

