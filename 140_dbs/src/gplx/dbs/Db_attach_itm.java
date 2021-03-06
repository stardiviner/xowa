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
package gplx.dbs; import gplx.*;
public class Db_attach_itm {
	public Db_attach_itm(String key, Io_url url) {
		this.Key = key; this.Url = url;
	}
	public Db_attach_itm(String key, Db_conn conn) {
		this.Key = key; this.Url = gplx.dbs.engines.sqlite.Sqlite_conn_info.To_url(conn);
	}
	public final String Key;
	public final Io_url Url;
}
