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
package gplx.xowa.mediawiki.includes.parsers.tables; import gplx.*; import gplx.xowa.*; import gplx.xowa.mediawiki.*; import gplx.xowa.mediawiki.includes.*; import gplx.xowa.mediawiki.includes.parsers.*;
import org.junit.*;
public class Xomw_table_wkr__tst {
	private final    Xomw_table_wkr__fxt fxt = new Xomw_table_wkr__fxt();
	@Test  public void Basic() {
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "{|"
		, "|-"
		, "|a"
		, "|}"
		), String_.Concat_lines_nl_skip_last
		( "<table>"
		, ""
		, "<tr>"
		, "<td>a"
		, "</td></tr></table>"
		));
	}		
	@Test  public void Tb__atrs() {
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "{|id='1'"
		, "|-"
		, "|a"
		, "|}"
		), String_.Concat_lines_nl_skip_last
		( "<table id=\"1\">"
		, ""
		, "<tr>"
		, "<td>a"
		, "</td></tr></table>"
		));
	}		
	@Test  public void Tc__atrs() {
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "{|"
		, "|+id='1'|a"
		, "|}"
		), String_.Concat_lines_nl_skip_last
		( "<table>"
		, "<caption id=\"1\">a"
		, "</caption><tr><td></td></tr></table>"
		));
	}
	@Test  public void Th__double() {
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "{|"
		, "!a!!b"
		, "|}"
		), String_.Concat_lines_nl_skip_last
		( "<table>"
		, "<tr>"
		, "<th>a</th>"
		, "<th>b"
		, "</th></tr></table>"
		));
	}		
	@Test  public void Blank() {	// COVERS: "empty line, go to next line"
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "   "
		), String_.Concat_lines_nl_skip_last
		( "   "
		));
	}
	@Test  public void Tb__indent() {
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "::{|"
		, "|-"
		, "|a"
		, "|}"
		), String_.Concat_lines_nl_skip_last
		( "<dl><dd><dl><dd><table>"
		, ""
		, "<tr>"
		, "<td>a"
		, "</td></tr></table></dd></dl></dd></dl>"
		));
	}
	@Test  public void Tb__empty() {	// COVERS: "if (has_opened_tr.Len() == 0) {"
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "{|"
		, "|}"
		), String_.Concat_lines_nl_skip_last
		( "<table>"
		, "<tr><td></td></tr></table>"
		));
	}
	@Test  public void Td__empty() {	// PURPOSE: handles (a) failure due to "first_2" array not handling "\n|\n"; (b) missing <tr><td></td></tr>
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "{|"
		, "|-"
		, "|"
		, "|}"
		), String_.Concat_lines_nl_skip_last
		( "<table>"
		, ""
		, "<tr>"
		, "<td>"
		, "</td></tr></table>"
		));
	}
}
class Xomw_table_wkr__fxt {
	private final    Xomw_parser_bfr parser_bfr = new Xomw_parser_bfr();
	private final    Xomw_parser_ctx pctx = new Xomw_parser_ctx();
	private final    Xomw_table_wkr wkr = new Xomw_table_wkr(new Xomw_parser());
	public void Test__parse(String src_str, String expd) {
		byte[] src_bry = Bry_.new_u8(src_str);
		parser_bfr.Init(src_bry);
		wkr.Do_table_stuff(pctx, parser_bfr);
		Tfds.Eq_str_lines(expd, parser_bfr.Rslt().To_str_and_clear(), src_str);
	}
}
