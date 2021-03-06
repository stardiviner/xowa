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
package gplx.xowa.xtns.pfuncs.times; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import org.junit.*;
public class Pft_func_time__other__tst {
	@Before	public void init()							{fxt.Clear();} private Pft_func_time_foreign_fxt fxt = new Pft_func_time_foreign_fxt();
	@After public void term()						{fxt.Term();}
	@Test   public void Thai()							{fxt.Test_parse("{{#time:xkY|2012}}"			, "2555");}
	@Test   public void Minguo()						{fxt.Test_parse("{{#time:xoY|2012}}"			, "101");}
	@Test   public void Iranian__year_idx()				{fxt.Test_parse("{{#time:xiY|2012-01-02}}"		, "1390");}
	@Test   public void Iranian__month_idx()			{fxt.Test_parse("{{#time:xin|2012-01-02}}"		, "10");}
	@Test   public void Iranian__day_idx()				{fxt.Test_parse("{{#time:xij|2012-01-02}}"		, "12");}
	@Test   public void Iranian__month_name()			{fxt.Init_msg("iranian-calendar-m10"	, "Dey"); fxt.Test_parse("{{#time:xiF|2012-01-02}}"		, "Dey");}
	@Test   public void Hijiri__year_idx()				{fxt.Test_parse("{{#time:xmY|2012-01-02}}"		, "1433");}
	@Test   public void Hijiri__month_idx()				{fxt.Test_parse("{{#time:xmn|2012-01-02}}"		, "2");}
	@Test   public void Hijiri__day_idx()				{fxt.Test_parse("{{#time:xmj|2012-01-02}}"		, "7");}
	@Test   public void Hijiri__month_name()			{fxt.Init_msg("hijiri-calendar-m2"	, "Safar"); fxt.Test_parse("{{#time:xmF|2012-01-02}}"		, "Safar");}
	@Test   public void Roman__year()					{fxt.Test_parse("{{#time:xrY|2012}}"			, "MMXII");}
	@Test   public void Roman__various() {
		fxt.Test_Roman(   1, "I");
		fxt.Test_Roman(   2, "II");
		fxt.Test_Roman(   3, "III");
		fxt.Test_Roman(   4, "IV");
		fxt.Test_Roman(   5, "V");
		fxt.Test_Roman(   6, "VI");
		fxt.Test_Roman(   7, "VII");
		fxt.Test_Roman(   8, "VIII");
		fxt.Test_Roman(   9, "IX");
		fxt.Test_Roman(  10, "X");
		fxt.Test_Roman(  11, "XI");
		fxt.Test_Roman( 100, "C");
		fxt.Test_Roman( 101, "CI");
		fxt.Test_Roman( 111, "CXI");
		fxt.Test_Roman(1000, "M");
		fxt.Test_Roman(1001, "MI");
		fxt.Test_Roman(4000, "4000");
	}
}
