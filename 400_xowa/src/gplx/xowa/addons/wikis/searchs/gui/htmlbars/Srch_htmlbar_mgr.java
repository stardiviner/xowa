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
package gplx.xowa.addons.wikis.searchs.gui.htmlbars; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.searchs.*; import gplx.xowa.addons.wikis.searchs.gui.*;
import gplx.core.net.*; import gplx.core.net.qargs.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.addons.wikis.searchs.searchers.cbks.*; import gplx.xowa.addons.wikis.searchs.searchers.*;
public class Srch_htmlbar_mgr implements Gfo_invk {
	private Srch_search_addon addon;
	private int results_max = 25;
	public void Init_by_kit(Xoae_app app, gplx.gfui.kits.core.Gfui_kit kit) {
		app.Cfg().Bind_many_app(this, Cfg__enabled, Cfg__results_max);
	}
	public boolean Enabled() {return enabled;} private boolean enabled = true;
	public void Search(Xowe_wiki wiki, byte[] search_bry, byte[] cbk_func) {
		if (	!enabled
			||	search_bry.length == 0
			) return;
		if (addon == null)
			addon = Srch_search_addon.Get(wiki);
		else {
			if (!Bry_.Eq(wiki.Domain_bry(), addon.Wiki_domain()))	// NOTE: suggest-box caches addon at wiki level; need to check if wiki has changed
				addon = Srch_search_addon.Get(wiki);
		}
		// tab_close_mgr.Add(this);
		Srch_search_qry qry = Srch_search_qry.New__suggest_box(wiki, wiki.App().Addon_mgr().Itms__search__special().Ns_mgr(), wiki.App().Addon_mgr().Itms__search__special().Auto_wildcard(), results_max, search_bry);
		Srch_rslt_cbk__suggest_box cbk = new Srch_rslt_cbk__suggest_box(wiki.Appe(), cbk_func, search_bry);
		addon.Search(qry, cbk);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Cfg__enabled))				enabled = m.ReadYn("v");
		else if	(ctx.Match(k, Cfg__results_max))			results_max = m.ReadInt("v");
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String
	  Cfg__enabled				= "xowa.addon.search.suggest.enabled"
	, Cfg__results_max			= "xowa.addon.search.suggest.results_max"
	;
}
