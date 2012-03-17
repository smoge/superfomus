// SuperFomus -- SuperCollider bindings to FoMus Music Notation
// Copyright (C) 2011 Bernardo Barros
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.


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
			
			if( this[\midinote].class == Array,
				{ 
					this[\midinote].do({arg thisNote;
						outString = outString ++ " pitch " ++ thisNote.asString + "; \n"
					})
				},{
					outString = outString ++ " pitch " ++ (this[\midinote]).asString;
					outString = outString ++ ";";
				}
			)
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
