package com.planet_ink.coffee_mud.CharClasses;
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
public class Gaian extends StdCharClass
{
	public String ID(){return "Gaian";}
	public String name(){return "Gaian";}
	public String baseClass(){return "Druid";}
	public int getBonusPracLevel(){return 2;}
	public int getBonusAttackLevel(){return 0;}
	public int getAttackAttribute(){return CharStats.STAT_CONSTITUTION;}
	public int getLevelsPerBonusDamage(){ return 30;}
	public int getHPDivisor(){return 2;}
	public int getHPDice(){return 2;}
	public int getHPDie(){return 7;}
	public int getManaDivisor(){return 4;}
	public int getManaDice(){return 1;}
	public int getManaDie(){return 4;}
	protected String armorFailMessage(){return "<S-NAME> watch(es) <S-HIS-HER> armor absorb <S-HIS-HER> magical energy!";}
	public int allowedArmorLevel(){return CharClass.ARMOR_NONMETAL;}
	public int allowedWeaponLevel(){return CharClass.WEAPONS_NATURAL;}
	private HashSet requiredWeaponMaterials=buildRequiredWeaponMaterials();
	protected HashSet requiredWeaponMaterials(){return requiredWeaponMaterials;}
	public int requiredArmorSourceMinor(){return CMMsg.TYP_CAST_SPELL;}

	public Gaian()
	{
		super();
		maxStatAdj[CharStats.STAT_CONSTITUTION]=4;
		maxStatAdj[CharStats.STAT_WISDOM]=4;
    }
    public void initializeClass()
    {
        super.initializeClass();
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Skill_Write",0,true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Skill_Recall",50,true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Skill_Revoke",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Skill_WandUse",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Skill_Swim",100,false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Skill_Climb",100,true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Specialization_Natural",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Herbology",0,false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Foraging",50,true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Druid_DruidicPass",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Druid_MyPlants",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Skill_WildernessLore",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Chant_SummonFlower",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),1,"Chant_SummonHerb",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),2,"Chant_LocatePlants",true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),3,"Chant_SummonFood",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),3,"Chant_SummonIvy",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),4,"Chant_SummonVine",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),4,"Chant_FreeVine",true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),5,"Specialization_BluntWeapon",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),5,"Chant_FortifyFood",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),6,"Chant_Barkskin",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),6,"Chant_SenseSentience",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),7,"Ranger_Hide",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),7,"Druid_KnowPlants",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),7,"Chant_Goodberry",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),7,"Chant_PlantSelf",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),8,"Chant_GrowClub",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),8,"Chant_Root",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),9,"Chant_PlantPass",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),9,"Chant_KillerVine",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),9,"PlantLore",true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),10,"Druid_PlantForm",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),10,"Herbalism",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),10,"Chant_SummonTree",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),11,"Farming",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),11,"Chant_VineWeave",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),11,"Chant_PlantBed",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),11,"Chant_SummonSeed",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),12,"Chant_Shillelagh",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),12,"Chant_PlantWall",true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),13,"Chant_DistantGrowth",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),13,"Chant_SummonFlyTrap",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),13,"Chant_SummonSeaweed",true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),14,"Chant_Bury",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),14,"Chant_PlantMaze",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),14,"Chant_Thorns",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),15,"Chant_PoisonousVine",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),15,"Chant_ControlPlant",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),15,"Chant_SummonHouseplant",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),15,"Chant_SensePlants",true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),16,"Chant_GrowItem",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),16,"Chant_Blight",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),17,"Chant_PlantSnare",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),17,"Chant_PlantConstriction",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),17,"Chant_FindPlant",true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),18,"Chant_VampireVine",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),18,"Chant_Chlorophyll",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),19,"Chant_DistantOvergrowth",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),19,"Chant_PlantChoke",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),20,"Chant_Grapevine",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),20,"Chant_SaplingWorkers",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),20,"Scrapping",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),21,"Chant_Treehouse",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),21,"Chant_VineMass",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),22,"Chant_GrowForest",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),22,"Chant_TapGrapevine",true);
		CMLib.ableMapper().addCharAbilityMapping(ID(),22,"Chant_GrowFood",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),23,"Chant_DistantIngrowth",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),23,"Chant_PlantTrap",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),24,"Chant_CharmArea",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),24,"Chant_SummonSapling",true);

		CMLib.ableMapper().addCharAbilityMapping(ID(),25,"Chant_SweetScent",false);
		CMLib.ableMapper().addCharAbilityMapping(ID(),25,"Chant_Shamblermorph",false);

		CMLib.ableMapper().addCharAbilityMapping(ID(),30,"Chant_GrowOak",true);
	}

	public int availabilityCode(){return Area.THEME_FANTASY;}

    public boolean isValidClassDivider(MOB killer, MOB killed, MOB mob, HashSet followers)
	{
		if((mob!=null)
        &&(mob!=killed)
		&&(!mob.amDead())
		&&((!mob.isMonster())||(!CMLib.flags().isVegetable(mob)))
		&&((mob.getVictim()==killed)
		 ||(followers.contains(mob))
		 ||(mob==killer)))
			return true;
		return false;
	}
	public String getStatQualDesc(){return "Constitution 9+, Wisdom 9+";}
	public boolean qualifiesForThisClass(MOB mob, boolean quiet)
	{
		if(mob != null)
		{
			if(mob.baseCharStats().getStat(CharStats.STAT_CONSTITUTION)<=8)
			{
				if(!quiet)
					mob.tell("You need at least a 9 Constitution to become a Gaian.");
				return false;
			}
			if(mob.baseCharStats().getStat(CharStats.STAT_WISDOM)<=8)
			{
				if(!quiet)
					mob.tell("You need at least a 9 Wisdom to become a Gaian.");
				return false;
			}
			if(!(mob.charStats().getMyRace().racialCategory().equals("Human"))
			&& !(mob.charStats().getMyRace().racialCategory().equals("Elf"))
			&& !(mob.charStats().getMyRace().racialCategory().equals("Vegetation"))
			&& !(mob.charStats().getMyRace().racialCategory().equals("Humanoid"))
			&& !(mob.charStats().getMyRace().racialCategory().equals("Halfling"))
			&& !(mob.charStats().getMyRace().racialCategory().equals("Dwarf")))
			{
				if(!quiet)
					mob.tell("You must be Human, Elf, Dwarf, Halfling, or Half Elf to be a Gaian");
				return false;
			}
		}
		return super.qualifiesForThisClass(mob,quiet);
	}

	public String getOtherLimitsDesc(){return "Must remain Neutral to avoid skill and chant failure chances.";}
	public String getOtherBonusDesc(){return "Attains Greenskin (sunlight based bonuses/penalties) at level 5.  At level 30, becomes totally undetectable in wilderness settings while hidden.  Can create a druidic connection with an area.  Benefits from animal/plant/stone followers leveling.  Benefits from freeing animals from cities.";}

    public void executeMsg(Environmental host, CMMsg msg){ super.executeMsg(host,msg); Druid.doAnimalFollowerLevelingCheck(this,host,msg);  Druid.doAnimalFreeingCheck(this,host,msg);}
    
	public void affectCharState(MOB affected, CharState affectableState)
	{
		super.affectCharState(affected,affectableState);
		if(affected.location()!=null)
		{
			Room room=affected.location();
			if(affected.charStats().getClassLevel(this)>=5)
			{
				if(CMLib.flags().isInDark(room))
				{
					affectableState.setMana(affectableState.getMana()-(affectableState.getMana()/4));
					affectableState.setMovement(affectableState.getMovement()-(affectableState.getMovement()/4));
				}
				else
				if((room.domainType()&Room.INDOORS)==0)
					switch(room.getArea().getClimateObj().weatherType(room))
					{
					case Climate.WEATHER_BLIZZARD:
					case Climate.WEATHER_CLOUDY:
					case Climate.WEATHER_DUSTSTORM:
					case Climate.WEATHER_HAIL:
					case Climate.WEATHER_RAIN:
					case Climate.WEATHER_SLEET:
					case Climate.WEATHER_SNOW:
					case Climate.WEATHER_THUNDERSTORM:
						break;
					default:
						affectableState.setMana(affectableState.getMana()+(affectableState.getMana()/4));
						affectableState.setMovement(affectableState.getMovement()+(affectableState.getMovement()/4));
						break;
					}
			}
		}

	}
	public boolean okMessage(Environmental myHost, CMMsg msg)
	{
		if(!(myHost instanceof MOB)) return super.okMessage(myHost,msg);
		MOB myChar=(MOB)myHost;
		if(!super.okMessage(myChar, msg))
			return false;

		if(msg.amISource(myChar)
		&&(!myChar.isMonster())
		&&(msg.sourceMinor()==CMMsg.TYP_CAST_SPELL)
		&&(msg.tool() instanceof Ability)
		&&((((Ability)msg.tool()).classificationCode()&Ability.ALL_ACODES)==Ability.ACODE_CHANT)
		&&(myChar.isMine(msg.tool()))
		&&(isQualifyingAuthority(myChar,(Ability)msg.tool()))
		&&(CMLib.dice().rollPercentage()<50))
		{
			if(((Ability)msg.tool()).appropriateToMyFactions(myChar))
				return true;
			myChar.tell("Extreme emotions disrupt your chant.");
			return false;
		}
		return true;
	}

	public void affectEnvStats(Environmental affected, EnvStats affectableStats)
	{
		super.affectEnvStats(affected,affectableStats);
		if((affected instanceof MOB)&&(((MOB)affected).location()!=null))
		{
			MOB mob=(MOB)affected;
			Room room=mob.location();
			int classLevel=mob.charStats().getClassLevel(this);
			if((CMLib.flags().isHidden(mob))
			&&(classLevel>=30)
			&&((room.domainType()&Room.INDOORS)==0)
			&&(room.domainType()!=Room.DOMAIN_OUTDOORS_CITY))
				affectableStats.setDisposition(affectableStats.disposition()|EnvStats.IS_NOT_SEEN);

			if(classLevel>=5)
			{
				if(CMLib.flags().isInDark(room))
					affectableStats.setAttackAdjustment(affectableStats.attackAdjustment()-((classLevel/5)+1));
				else
				if((room.domainType()&Room.INDOORS)==0)
					switch(room.getArea().getClimateObj().weatherType(room))
					{
					case Climate.WEATHER_BLIZZARD:
					case Climate.WEATHER_CLOUDY:
					case Climate.WEATHER_DUSTSTORM:
					case Climate.WEATHER_HAIL:
					case Climate.WEATHER_RAIN:
					case Climate.WEATHER_SLEET:
					case Climate.WEATHER_SNOW:
					case Climate.WEATHER_THUNDERSTORM:
						break;
					default:
						affectableStats.setAttackAdjustment(affectableStats.attackAdjustment()+((classLevel/5)+1));
						break;
					}
			}
		}
	}

	public Vector outfit(MOB myChar)
	{
		if(outfitChoices==null)
		{
			outfitChoices=new Vector();
			Weapon w=CMClass.getWeapon("Quarterstaff");
			outfitChoices.addElement(w);
		}
		return outfitChoices;
	}

	
	public void grantAbilities(MOB mob, boolean isBorrowedClass)
	{
		super.grantAbilities(mob,isBorrowedClass);
		if(mob.playerStats()==null)
		{
			DVector V=CMLib.ableMapper().getUpToLevelListings(ID(),
												mob.charStats().getClassLevel(ID()),
												false,
												false);
			for(Enumeration a=V.getDimensionVector(1).elements();a.hasMoreElements();)
			{
				Ability A=CMClass.getAbility((String)a.nextElement());
				if((A!=null)
				&&((A.classificationCode()&Ability.ALL_ACODES)==Ability.ACODE_CHANT)
				&&(!CMLib.ableMapper().getDefaultGain(ID(),true,A.ID())))
					giveMobAbility(mob,A,CMLib.ableMapper().getDefaultProficiency(ID(),true,A.ID()),CMLib.ableMapper().getDefaultParm(ID(),true,A.ID()),isBorrowedClass);
			}
		}
	}

	public int classDurationModifier(MOB myChar,
									 Ability skill,
									 int duration)
	{
		if(myChar==null) return duration;
		if(((skill.classificationCode()&Ability.ALL_DOMAINS)==Ability.DOMAIN_CRAFTINGSKILL)
		&&(myChar.charStats().getCurrentClass().ID().equals(ID()))
		&&(!skill.ID().equals("FoodPrep"))
		&&(!skill.ID().equals("Cooking"))
		&&(!skill.ID().equals("Herbalism"))
		&&(!skill.ID().equals("Weaving"))
		&&(!skill.ID().equals("Masonry")))
			return duration*2;

		return duration;
	}
}
