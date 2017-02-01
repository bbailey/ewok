package com.planet_ink.coffee_mud.WebMacros;

import com.planet_ink.coffee_web.interfaces.*;
import com.planet_ink.coffee_mud.core.interfaces.*;
import com.planet_ink.coffee_mud.core.*;
import com.planet_ink.coffee_mud.core.collections.*;
import com.planet_ink.coffee_mud.Abilities.interfaces.*;
import com.planet_ink.coffee_mud.Areas.interfaces.*;
import com.planet_ink.coffee_mud.Behaviors.interfaces.*;
import com.planet_ink.coffee_mud.CharClasses.interfaces.*;
import com.planet_ink.coffee_mud.Libraries.interfaces.*;
import com.planet_ink.coffee_mud.Common.interfaces.*;
import com.planet_ink.coffee_mud.Exits.interfaces.*;
import com.planet_ink.coffee_mud.Items.interfaces.*;
import com.planet_ink.coffee_mud.Locales.interfaces.*;
import com.planet_ink.coffee_mud.MOBS.interfaces.*;
import com.planet_ink.coffee_mud.Races.interfaces.*;

import java.util.*;



/*
   Copyright 2013-2016 Bo Zimmerman

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
public class CatalogCatNext extends StdWebMacro
{
	@Override public String name() { return "CatalogCatNext"; }

	@Override
	public String runMacro(HTTPRequest httpReq, String parm, HTTPResponse httpResp)
	{
		final java.util.Map<String,String> parms=parseParms(parm);
		String last=httpReq.getUrlParameter("CATACAT");
		if(parms.containsKey("RESET"))
		{
			if(last!=null)
				httpReq.removeUrlParameter("CATACAT");
			return "";
		}
		final boolean mobs=parms.containsKey("MOBS")||parms.containsKey("MOB");
		if(!httpReq.getRequestObjects().containsKey("CATACATS"+(mobs?"M":"I")))
			httpReq.getRequestObjects().put("CATACATS"+(mobs?"M":"I"), mobs?CMLib.catalog().getMobCatalogCatagories():CMLib.catalog().getItemCatalogCatagories());
		final String[] cats=(String[])httpReq.getRequestObjects().get("CATACATS"+(mobs?"M":"I"));
		if(parms.containsKey("WIDTH"))
			return ""+100/(cats.length+1);
		String lastID=null;
		if((last!=null)&&(last.equalsIgnoreCase("")))
			last="UNCATEGORIZED";
		for(String cat : cats)
		{
			if(cat.length()==0)
				cat="UNCATEGORIZED";
			if((last==null)||((lastID!=null)&&(last.equals(lastID))&&(!cat.equals(lastID))))
			{
				httpReq.addFakeUrlParameter("CATACAT",cat);
				return "";
			}
			lastID=cat;
		}
		httpReq.addFakeUrlParameter("CATACAT","");
		if(parms.containsKey("EMPTYOK"))
			return "<!--EMPTY-->";
		return " @break@";
	}

}