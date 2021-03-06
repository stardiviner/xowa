/*
XOWA: the XOWA Offline Wiki Application
Copyright (C) 2012 gnosygnu@gmail.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package gplx.xowa.xtns.pfuncs.stringutils; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import org.junit.*;
public class Pfunc_explode_tst {
	@Before public void init()						{fxt.Reset();} private final Xop_fxt fxt = Xop_fxt.new_nonwmf();
	@Test   public void Idx_1st()					{fxt.Test_parse_template("{{#explode:a,b,c|,|0}}"				, "a");}
	@Test   public void Idx_mid()					{fxt.Test_parse_template("{{#explode:a,b,c|,|1}}"				, "b");}
	@Test   public void Idx_nth()					{fxt.Test_parse_template("{{#explode:a,b,c|,|2}}"				, "c");}
	@Test   public void Idx_missing()				{fxt.Test_parse_template("{{#explode:a,b,c|,|3}}"				, "");}
	@Test   public void Idx_neg()					{fxt.Test_parse_template("{{#explode:a,b,c|,|-1}}"				, "b");}
	@Test   public void Find_defaults_to_space()	{fxt.Test_parse_template("{{#explode:a b c||0}}"				, "a");}
}
