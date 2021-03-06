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
package gplx.core.srls; import gplx.*; import gplx.core.*;
public interface Gfo_srl_mgr_rdr {
	void Itm_bgn(String key);
	void Itm_end();
	boolean			Get_bool	(String key);
	int				Get_int		(String key);
	String			Get_str		(String key);
	Object			Get_subs	(Gfo_srl_ctx ctx, Gfo_srl_itm owner, Gfo_srl_itm proto, Dbmeta_dat_mgr crt_mgr);
}
