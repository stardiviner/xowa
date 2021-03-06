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
package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
import gplx.dbs.*;
class Site_showhook_tbl implements Db_tbl {
	private final    Dbmeta_fld_list flds = new Dbmeta_fld_list();
	private final    String fld_site_abrv, fld_name, fld_scribunto, fld_subscribers;
	private final    Db_conn conn;
	private Db_stmt stmt_select, stmt_insert, stmt_delete;
	public Site_showhook_tbl(Db_conn conn) {
		this.conn = conn;
		this.fld_site_abrv				= flds.Add_str("site_abrv", 255);
		this.fld_name					= flds.Add_str("name", 255);
		this.fld_scribunto				= flds.Add_str("scribunto", 255);
		this.fld_subscribers			= flds.Add_str("subscribers", 2048);
		conn.Rls_reg(this);
	}
	public String Tbl_name() {return tbl_name;} private static final String tbl_name = "site_showhook";
	public void Create_tbl() {conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds, Dbmeta_idx_itm.new_unique_by_name(tbl_name, Dbmeta_idx_itm.Bld_idx_name(tbl_name, "main"), fld_site_abrv, fld_name)));}
	public void Delete_all() {conn.Stmt_delete(tbl_name, Dbmeta_fld_itm.Str_ary_empty).Exec_delete();}
	public void Rls() {
		stmt_select = Db_stmt_.Rls(stmt_select);
		stmt_insert = Db_stmt_.Rls(stmt_insert);
		stmt_delete = Db_stmt_.Rls(stmt_delete);
	}
	public void Select(byte[] site_abrv, Ordered_hash list) {
		if (stmt_select == null) stmt_select = conn.Stmt_select(tbl_name, flds, fld_site_abrv);
		list.Clear();
		Db_rdr rdr = stmt_select.Clear().Crt_bry_as_str(fld_site_abrv, site_abrv).Exec_select__rls_auto();
		try {
			while (rdr.Move_next()) {
				Site_showhook_itm itm = new Site_showhook_itm
				( rdr.Read_bry_by_str(fld_name)
				, rdr.Read_bry_by_str(fld_scribunto)
				, Bry_split_.Split(rdr.Read_bry_by_str(fld_subscribers), Byte_ascii.Pipe_bry)
				);
				list.Add(itm.Name(), itm);
			}
		}
		finally {rdr.Rls();}
	}
	public void Insert(byte[] site_abrv, Ordered_hash list) {
		if (stmt_delete == null) stmt_delete = conn.Stmt_delete(tbl_name, fld_site_abrv);
		if (stmt_insert == null) stmt_insert = conn.Stmt_insert(tbl_name, flds);
		stmt_delete.Clear().Crt_bry_as_str(fld_site_abrv, site_abrv).Exec_delete();
		int len = list.Count();
		for (int i = 0; i < len; ++i) {
			Site_showhook_itm itm = (Site_showhook_itm)list.Get_at(i);
			Insert(site_abrv, itm.Name(), itm.Scribunto(), itm.Subscribers());
		}
	}
	private void Insert(byte[] site_abrv, byte[] name, byte[] scribunto, byte[][] subscribers) {
		stmt_insert.Clear()
			.Val_bry_as_str(fld_site_abrv		, site_abrv)
			.Val_bry_as_str(fld_name			, name)
			.Val_bry_as_str(fld_scribunto		, scribunto)
			.Val_bry_as_str(fld_subscribers		, Bry_.Add_w_dlm(Byte_ascii.Pipe, subscribers))
			.Exec_insert();
	}		
}
