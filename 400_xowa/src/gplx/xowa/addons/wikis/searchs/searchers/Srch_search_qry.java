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
package gplx.xowa.addons.wikis.searchs.searchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.searchs.*;
import gplx.xowa.wikis.domains.*;
import gplx.xowa.addons.wikis.searchs.gui.htmlbars.*;
import gplx.xowa.addons.wikis.searchs.searchers.crts.*;
public class Srch_search_qry {
	public Srch_search_qry(byte tid, Srch_ns_mgr ns_mgr, Srch_search_phrase phrase, int slab_bgn, int slab_end) {
		this.Tid = tid;
		this.Ns_mgr = ns_mgr;
		this.Phrase = phrase;
		this.Slab_bgn = slab_bgn;
		this.Slab_end = slab_end;
	}
	public final    byte				Tid;
	public final    Srch_ns_mgr			Ns_mgr;
	public final    Srch_search_phrase	Phrase;
	public final    int					Slab_bgn;		// EX: 0
	public final    int					Slab_end;		// EX: 20

	public static final byte Tid_len = 4, Tid__url_bar = 0, Tid__suggest_box = 1, Tid__search_page = 2, Tid__android = 3;
	public static Srch_search_qry New__url_bar(Xow_wiki wiki, Srch_ns_mgr ns_mgr, Srch_crt_scanner_syms syms, boolean auto_wildcard, int max_results, byte[] search_orig) {
		return new Srch_search_qry(Tid__url_bar, ns_mgr, Srch_search_phrase.New(wiki.Case_mgr(), syms, auto_wildcard, search_orig), 0, max_results);
	}
	public static Srch_search_qry New__suggest_box(Xow_wiki wiki, Srch_ns_mgr ns_mgr, boolean auto_wildcard, int results_max, byte[] search_orig) {
		return new Srch_search_qry(Tid__suggest_box, ns_mgr, Srch_search_phrase.New(wiki.Case_mgr(), Srch_crt_scanner_syms.Dflt, auto_wildcard, search_orig), 0, results_max);
	}
	public static Srch_search_qry New__search_page(Xow_domain_itm[] domains, Xow_wiki wiki, Srch_ns_mgr ns_mgr, boolean auto_wildcard, byte[] search_orig, int slab_idx, int slab_len) {
		int slab_bgn = slab_idx * slab_len;
		int slab_end = slab_bgn + slab_len;
		return new Srch_search_qry(Tid__search_page, ns_mgr, Srch_search_phrase.New(wiki.Case_mgr(), Srch_crt_scanner_syms.Dflt, auto_wildcard, search_orig), slab_bgn, slab_end);
	}
	public static Srch_search_qry New__drd(Xow_wiki wiki, Srch_ns_mgr ns_mgr, byte[] search_orig, int slab_bgn, int slab_end) {
		return new Srch_search_qry(Tid__android, ns_mgr, Srch_search_phrase.New(wiki.Case_mgr(), Srch_crt_scanner_syms.Dflt, Bool_.Y, search_orig), slab_bgn, slab_end);
	}
}
