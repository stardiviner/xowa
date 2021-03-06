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
package gplx.xowa.wikis.data.site_stats; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.dbs.*; import gplx.xowa.wikis.data.site_stats.*;
public class Xowd_site_stats_tbl implements Db_tbl {		
	private final    String fld_row_id, fld_good_articles, fld_total_pages, fld_images;
	private final    Db_conn conn; private final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	public Xowd_site_stats_tbl(Db_conn conn, boolean schema_is_1) {
		this.conn = conn;
		fld_row_id			= flds.Add_int_pkey("ss_row_id");
		fld_good_articles	= flds.Add_long("ss_good_articles");
		fld_total_pages		= flds.Add_long("ss_total_pages");
		fld_images			= flds.Add_int("ss_images");
	}
	public String Tbl_name() {return tbl_name;} private final    String tbl_name = TBL_NAME;
	public void Create_tbl() {
		conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));
		conn.Stmt_insert(tbl_name, flds).Val_int(fld_row_id, Site_stats_row_id).Val_long(fld_good_articles, 0).Val_long(fld_total_pages, 0).Val_int(fld_images, 0).Exec_insert();
	}
	public void Update(long num_articles, long num_pages, int num_files) {
		Gfo_usr_dlg_.Instance.Log_many("", "", "db.site_stats: update started: num_articles=~{0} num_pages=~{1} num_files=~{2}", num_articles, num_pages, num_files);
		Db_stmt stmt = conn.Stmt_update(tbl_name, String_.Ary(fld_row_id), fld_good_articles, fld_total_pages, fld_images);
		stmt.Val_long(fld_good_articles, num_articles).Val_long(fld_total_pages, num_pages).Val_int(fld_images, num_files)
			.Crt_int(fld_row_id, Site_stats_row_id)
			.Exec_update();
		Gfo_usr_dlg_.Instance.Log_many("", "", "db.site_stats: update done");
	}
	public void Select(Xowd_site_stats_mgr stats) {
		Db_rdr rdr = conn.Stmt_select(tbl_name, flds, fld_row_id).Crt_int(fld_row_id, Site_stats_row_id).Exec_select__rls_auto();
		try {
			if (rdr.Move_next()) {
				stats.Load_by_db
				( rdr.Read_long(fld_total_pages)
				, rdr.Read_long(fld_good_articles)
				, rdr.Read_int(fld_images)
				, 0, 0, 0, 0, 0);
			}
		} finally {rdr.Rls();}
	}
	public void Rls() {}
	private static final int Site_stats_row_id = 1;

	public static final String TBL_NAME = "site_stats";
	public static Xowd_site_stats_tbl Get_by_key(Db_tbl_owner owner) {return (Xowd_site_stats_tbl)owner.Tbls__get_by_key(TBL_NAME);}
}
