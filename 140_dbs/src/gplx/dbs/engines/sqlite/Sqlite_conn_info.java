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
package gplx.dbs.engines.sqlite; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Sqlite_conn_info extends Db_conn_info__base {
	public Sqlite_conn_info(String raw, String db_api, String database, Io_url url) {super(raw, db_api, database);this.url = url;}
	@Override public String Key() {return Key_const;} public static final String Key_const = "sqlite";
	public Io_url Url() {return url;} private final Io_url url;
	@Override public Db_conn_info New_self(String raw, Keyval_hash hash) {
		Io_url url = Io_url_.new_any_(hash.Get_val_as_str_or_fail("data source"));
		String db = url.NameOnly();
		String api = Bld_api(hash, Keyval_.new_("version", "3"));
		return new Sqlite_conn_info(raw, api, db, url);
	}

	public static final Sqlite_conn_info Instance = new Sqlite_conn_info("", "", "", Io_url_.Empty);
	public static Db_conn_info load_(Io_url url) {
		return Db_conn_info_.parse(Bld_raw
		( "gplx_key"		, Key_const
		, Cs__data_source	, url.Xto_api()
		, Cs__version		, Cs__version__3
		));
	}
	public static Db_conn_info make_(Io_url url) {
		Io_mgr.Instance.CreateDirIfAbsent(url.OwnerDir());
		return Db_conn_info_.parse(Bld_raw
		( "gplx_key"		, Key_const
		, Cs__data_source	, url.Xto_api()
		, Cs__version		, Cs__version__3
			
		));
	}
	public static final String Cs__data_source = "data source", Cs__version = "version", Cs__version__3 = "3";
	public static Io_url To_url(Db_conn conn) {return ((Sqlite_conn_info)conn.Conn_info()).url;}
}