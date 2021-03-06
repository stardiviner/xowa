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
package gplx.xowa.mediawiki.includes.parsers.quotes; import gplx.*; import gplx.xowa.*; import gplx.xowa.mediawiki.*; import gplx.xowa.mediawiki.includes.*; import gplx.xowa.mediawiki.includes.parsers.*;
import org.junit.*;
public class Xomw_quote_wkr__tst {
	private final    Xomw_quote_wkr__fxt fxt = new Xomw_quote_wkr__fxt();
	@Test  public void Apos__0()       {fxt.Test__parse("abc"                         , "abc");}
	@Test  public void Apos__1()       {fxt.Test__parse("a'b'c"                       , "a'b'c");}
	@Test  public void Apos__2()       {fxt.Test__parse("a''b''c"                     , "a<i>b</i>c");}
	@Test  public void Apos__3()       {fxt.Test__parse("a'''b'''c"                   , "a<b>b</b>c");}
	@Test  public void Apos__4()       {fxt.Test__parse("a''''b''''c"                 , "a'<b>b'</b>c");}            // COVERS: "If there are ever four apostrophes"
	@Test  public void Apos__5()       {fxt.Test__parse("a'''''b'''''c"               , "a<i><b>b</b></i>c");}
	@Test  public void Apos__7()       {fxt.Test__parse("a'''''''b'''''''c"           , "a''<i><b>b''</b></i>c");}   // COVERS: "If there are more than 5 apostrophes in a row"
	@Test  public void Mix__single()   {fxt.Test__parse("''a ''' ''b b''' ''cc'''"    , "<i>a <b> </b></i><b>b b'<i> </i>cc</b>");}   // COVERS: "If there is a single-letter word, use it!"
	@Test  public void Mix__multi()    {fxt.Test__parse("''a ''' ''b ''' ''cc'''"     , "<i>a <b> </b></i><b>b </b> <i>cc'</i>");}    // COVERS: "If not, but there's a multi-letter word, use that one."
	@Test  public void Mix__space()    {fxt.Test__parse("''a ''' ''b ''' ''c '''"     , "<i>a '</i> <i>b <b> </b></i><b>c </b>");}    // COVERS: "... otherwise use the first one that has neither."
	@Test  public void Dangling__b()   {fxt.Test__parse("a'''b"                       , "a<b>b</b>");}               // COVERS: "if (state == State__b || state == State__ib)"
	@Test  public void Dangling__i()   {fxt.Test__parse("a''b"                        , "a<i>b</i>");}               // COVERS: "if (state == State__i || state == State__bi || state == State__ib)"
	@Test  public void Dangling__lone(){fxt.Test__parse("a'''''b"                     , "a<b><i>b</i></b>");}        // COVERS: "There might be lonely ''''', so make sure we have a buffer"
	@Test  public void Nl__text()      {fxt.Test__parse("a\nb''c''d\n\ne"             , "a\nb<i>c</i>d\n\ne");}
}
class Xomw_quote_wkr__fxt {
	private final    Xomw_quote_wkr wkr = new Xomw_quote_wkr(new Xomw_parser());
	private final    Xomw_parser_bfr pbfr = new Xomw_parser_bfr();
	public void Test__parse(String src_str, String expd) {
		byte[] src_bry = Bry_.new_u8(src_str);
		wkr.Do_all_quotes(new Xomw_parser_ctx(), pbfr.Init(src_bry));
		Tfds.Eq_str_lines(expd, pbfr.Rslt().To_str_and_clear(), src_str);
	}
}
