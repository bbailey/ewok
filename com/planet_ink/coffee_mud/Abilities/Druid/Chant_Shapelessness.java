package com.planet_ink.coffee_mud.Abilities.Druid;
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
public class Chant_Shapelessness extends Chant
{
	public String ID() { return "Chant_Shapelessness"; }
	public String name(){ return "Shapelessness";}
	public String displayText(){ return "(Shapelessness)";}
    public int classificationCode(){return Ability.ACODE_CHANT|Ability.DOMAIN_SHAPE_SHIFTING;}
	protected int canAffectCode(){return CAN_MOBS;}
	protected int canTargetCode(){return 0;}
	public int abstractQuality(){ return Ability.QUALITY_BENEFICIAL_SELF;}


	public void unInvoke()
	{
		// undo the affects of this spell
		if((affected==null)||(!(affected instanceof MOB)))
			return;
		MOB mob=(MOB)affected;

		super.unInvoke();

		if(canBeUninvoked())
			if((mob.location()!=null)&&(!mob.amDead()))
				mob.location().show(mob,null,CMMsg.MSG_OK_VISUAL,"<S-NAME> return(s) to material form.");
	}

	public void affectEnvStats(Environmental affected, EnvStats affectableStats)
	{
		super.affectEnvStats(affected,affectableStats);
		affectableStats.setWeight(0);
		affectableStats.setHeight(-1);
	}

	public boolean okMessage(Environmental myHost, CMMsg msg)
	{
		if((affected!=null)
		&&(affected instanceof MOB)
		&&(msg.amISource((MOB)affected))
		&&(CMLib.dice().rollPercentage()>25))
		{
			switch(msg.sourceMinor())
			{
			case CMMsg.TYP_ENTER:
			case CMMsg.TYP_LEAVE:
				if((msg.tool() instanceof Exit)
				&&(((Exit)msg.tool()).hasADoor())
				&&(!((Exit)msg.tool()).isOpen())
				&&(msg.source().inventorySize()>0))
				{
					msg.source().tell("Your corporeal equipment, suspended in your shapeless form, will not pass through the door.");
					return false;
				}
				break;
			case CMMsg.TYP_GET:
			case CMMsg.TYP_PUT:
			case CMMsg.TYP_DROP:
			case CMMsg.TYP_HOLD:
			case CMMsg.TYP_WIELD:
			case CMMsg.TYP_WEAR:
			case CMMsg.TYP_REMOVE:
			case CMMsg.TYP_DELICATE_HANDS_ACT:
			case CMMsg.TYP_WITHDRAW:
			case CMMsg.TYP_BORROW:
			case CMMsg.TYP_LOCK:
			case CMMsg.TYP_UNLOCK:
			case CMMsg.TYP_HANDS:
				msg.source().tell("You have trouble manipulating matter in this form.");
				return false;
			case CMMsg.TYP_THROW:
			case CMMsg.TYP_WEAPONATTACK:
			case CMMsg.TYP_KNOCK:
			case CMMsg.TYP_PULL:
			case CMMsg.TYP_PUSH:
			case CMMsg.TYP_OPEN:
			case CMMsg.TYP_CLOSE:
				msg.source().tell("You fail your attempt to affect matter in this form.");
				return false;
			}
		}
		return super.okMessage(myHost,msg);
	}

    public int castingQuality(MOB mob, Environmental target)
    {
        if(mob!=null)
        {
            if(target instanceof MOB)
            {
                if(((MOB)target).isInCombat())
                    return Ability.QUALITY_INDIFFERENT;
            }
        }
        return super.castingQuality(mob,target);
    }

	public boolean invoke(MOB mob, Vector commands, Environmental givenTarget, boolean auto, int asLevel)
	{
		MOB target=mob;
		if((auto)&&(givenTarget!=null)&&(givenTarget instanceof MOB))
			target=(MOB)givenTarget;
		if(target.fetchEffect(ID())!=null)
		{
			mob.tell(target,null,null,"<S-NAME> <S-IS-ARE> already shapeless.");
			return false;
		}
		if((!auto)&&(!mob.location().getArea().getClimateObj().canSeeTheMoon(mob.location(),null)))
		{
			mob.tell("You must be able under the moons glow for this magic to work.");
			return false;
		}

		if(!super.invoke(mob,commands,givenTarget,auto,asLevel))
			return false;

		boolean success=proficiencyCheck(mob,0,auto);

		if(success)
		{
			// it worked, so build a copy of this ability,
			// and add it to the affects list of the
			// affected MOB.  Then tell everyone else
			// what happened.
			CMMsg msg=CMClass.getMsg(mob,target,this,verbalCastCode(mob,target,auto),auto?"":"^S<S-NAME> chant that <T-NAME> be given a shapeless form.^?");
			if(mob.location().okMessage(mob,msg))
			{
				mob.location().send(mob,msg);
				mob.location().show(target,null,CMMsg.MSG_OK_VISUAL,"<S-NAME> shimmer(s) and become(s) ethereal!");
				beneficialAffect(mob,target,asLevel,3);
			}
		}
		else
			return beneficialWordsFizzle(mob,target,"<S-NAME> chant(s) for a new shape, but nothing happens.");


		// return whether it worked
		return success;
	}
}