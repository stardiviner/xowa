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
package gplx.xowa; import gplx.*;
public class Xoa_page_ {
	public static final byte Edit_mode_create = 1, Edit_mode_update = 2;
	public static final String Main_page_str = "Main_Page";
	public static final    byte[] Main_page_bry = Bry_.new_a7(Main_page_str);	// NOTE; may not work for non-english wikis
	public static final    byte[] Main_page_bry_empty = Bry_.Empty;
	public static final int Page_len_max = 2048 * Io_mgr.Len_kb;	// REF.MW: DefaultSettings.php; $wgMaxArticleSize = 2048;
	public static byte[] Url_bry_safe(Xoa_url url, Xow_wiki wiki, Xoa_ttl ttl) {
		byte[] rv = url == null ? Bry_.Empty : url.To_bry(Bool_.Y, Bool_.Y);
		if (Bry_.Len_eq_0(rv))
			rv = Bry_.Add(wiki == null ? Bry_.Empty : wiki.Domain_bry(), gplx.xowa.htmls.hrefs.Xoh_href_.Bry__wiki, ttl == null ? Bry_.Empty : ttl.Full_db());
		return rv;
	}
}
