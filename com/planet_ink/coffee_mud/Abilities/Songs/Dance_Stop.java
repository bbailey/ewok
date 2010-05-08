package com.planet_ink.coffee_mud.Abilities.Songs;
import com.planet_ink.coffee_mud.core.interfaces.*;
import com.planet_ink.coffee_mud.core.*;
import com.planet_ink.coffee_mud.Abilities.interfaces.*;
import com.planet_ink.coffee_mud.Areas.interfaces.*;
import com.planet_ink.coffee_mud.Behaviors.interfaces.*;
import com.planet_ink.coffee_mud.CharClasses.interfaces.*;
import com.planet_ink.coffee_mud.Commands.interfaces.*;
import com.planet_ink.coffee_mud.Common.interfaces.*;
import com.planet_ink.coffee_mud.Exits.interfaces.*;
import com.planet_ink.coffee_mud.Items.interfaces.*;
import com.planet_ink.coffee_mud.Locales.interfaces.*;
import com.planet_ink.coffee_mud.MOBS.interfaces.*;
import com.planet_ink.coffee_mud.Races.interfaces.*;

import java.util.*;


/* 
   Copyright 2000-2010 Bo Zimmerman

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
@SuppressWarnings("unchecked")
public class Dance_Stop extends Dance
{
	public String ID() { return "Dance_Stop"; }
	public String name(){ return "Stop";}
	public int abstractQuality(){ return Ability.QUALITY_INDIFFERENT;}
	protected boolean skipStandardSongInvoke(){return true;}
	public Dance_Stop()
	{
		super();
		setProficiency(100);
	}
	public void setProficiency(int newProficiency){	super.setProficiency(100);}


	public boolean invoke(MOB mob, Vector commands, Environmental givenTarget, boolean auto, int asLevel)
	{
		boolean foundOne=false;
		for(int a=0;a<mob.numEffects();a++)
		{
			Ability A=mob.fetchEffect(a);
			if((A!=null)&&(A instanceof Dance))
				foundOne=true;
		}
		undance(mob,null,false);
		if(!foundOne)
		{
			mob.tell(auto?"There is no dance going.":"You aren't dancing.");
			return true;
		}

		mob.location().show(mob,null,CMMsg.MSG_NOISE,auto?"Rest.":"<S-NAME> stop(s) dancing.");
		mob.location().recoverRoomStats();
		return true;
	}
}