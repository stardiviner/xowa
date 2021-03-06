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
package gplx.xowa.addons.parsers.mediawikis; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.parsers.*;
public class Xop_mediawiki_mgr {
	private final    Xoae_app app;
	private boolean mode_is_prod;
	public Xop_mediawiki_mgr(String root_str, boolean mode_is_prod) {
		Gfo_usr_dlg usr_dlg = Xoa_app_.New__usr_dlg__console();
		Gfo_usr_dlg_.Instance = usr_dlg;
		Io_url root_dir = Io_url_.new_dir_(root_str);

		this.mode_is_prod = mode_is_prod;
		if (mode_is_prod) {
			gplx.dbs.Db_conn_bldr.Instance.Reg_default_sqlite();
			gplx.core.envs.Env_.Init_swt(String_.Ary_empty, Type_adp_.ClassOf_obj(this));	// must call Init else unit_testing will be true
		}
		this.app = new Xoae_app(usr_dlg, gplx.xowa.apps.Xoa_app_mode.Itm_cmd
		, root_dir
		, root_dir.GenSubDir("wiki")
		, root_dir.GenSubDir("file")
		, root_dir.GenSubDir("user")
		, root_dir.GenSubDir_nest("user", "anonymous", "wiki")
		, gplx.xowa.apps.boots.Xoa_cmd_arg_mgr.Bin_dir_name()
		);
		if (mode_is_prod) {
			app.Init_by_app();
			app.Stage_(gplx.xowa.apps.Xoa_stage_.Tid_launch);	// must set to Launch, else wiki.init_needed will never be false; DATE:2017-01-26
		}
	}
	public Xop_mediawiki_wkr Make(String domain_str) {return Make(domain_str, null);}
	public Xop_mediawiki_wkr Make(String domain_str, Xop_mediawiki_loader loader) {
		Xowe_wiki wiki = (Xowe_wiki)app.Wiki_mgr().Make(Bry_.new_u8(domain_str), app.Fsys_mgr().Wiki_dir());
		if (mode_is_prod) {
			wiki.Init_by_wiki();
			wiki.File_mgr().Version_2_y_(); // must set to version_2 else video files will use old v1 Meta_code; DATE:2017-01-26
			wiki.File_mgr().Fsdb_mode().Tid__v2__mp__y_();	// must set to mass_parse mode, else will use old v1 Meta_code for xfer_itm and url_bldr; DATE:2017-01-26
		}
		return new Xop_mediawiki_wkr(wiki, loader);
	}

	public static Xop_mediawiki_mgr New(String root_str) {
		return new Xop_mediawiki_mgr(root_str, true);
	}
}
