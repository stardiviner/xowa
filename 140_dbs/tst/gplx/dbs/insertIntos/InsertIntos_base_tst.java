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
package gplx.dbs.insertIntos; import gplx.*; import gplx.dbs.*;
import org.junit.*; import gplx.core.gfo_ndes.*; import gplx.dbs.qrys.*; import gplx.core.stores.*;
public abstract class InsertIntos_base_tst {
	protected abstract Db_conn provider_();
	protected Db_conn conn;
	@Before public void setup() {
		conn = provider_();
		conn.Exec_qry(Db_qry_delete.new_("dbs_group_bys"));
		conn.Exec_qry(Db_qry_delete.new_("dbs_insert_intos"));
	}
	@After public void teardown() {
		conn.Rls_conn();
	}
	protected void Select_hook() {
		conn.Exec_qry(Db_qry_.insert_("dbs_group_bys").Val_str("key1", "a").Val_int("val_int", 1));

		conn.Exec_qry
			(Db_qry_.insert_("dbs_insert_intos")
			.Cols_("key1", "val_int")
			.Select_
			(	new Db_qry__select_cmd().Cols_("key1", "val_int").From_("dbs_group_bys")
			)
			);
		DataRdr rdr = conn.Exec_qry_as_old_rdr(new Db_qry__select_cmd().Cols_("key1", "val_int").From_("dbs_insert_intos"));
		GfoNde nde = GfoNde_.rdr_(rdr);
		GfoNdeTstr.tst_ValsByCol(nde, "key1", "a");
	}
	protected void GroupBy_hook() {
		conn.Exec_qry(Db_qry_.insert_("dbs_group_bys").Val_str("key1", "a").Val_int("val_int", 1));
		conn.Exec_qry(Db_qry_.insert_("dbs_group_bys").Val_str("key1", "a").Val_int("val_int", 2));

		conn.Exec_qry
			(Db_qry_.insert_("dbs_insert_intos")
			.Cols_("key1", "val_int")
			.Select_
			(	new Db_qry__select_cmd().Cols_("key1").Cols_groupBy_sum("val_int", "val_int_func")
				.From_("dbs_group_bys").GroupBy_("key1")
			));
		DataRdr rdr = conn.Exec_qry_as_old_rdr(new Db_qry__select_cmd().Cols_("key1", "val_int").From_("dbs_insert_intos"));
		GfoNde nde = GfoNde_.rdr_(rdr);
		GfoNdeTstr.tst_ValsByCol(nde, "val_int", 3);
	}
}
