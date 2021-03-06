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
package gplx.xowa.mediawiki.includes.parsers.lnkis; import gplx.*; import gplx.xowa.*; import gplx.xowa.mediawiki.*; import gplx.xowa.mediawiki.includes.*; import gplx.xowa.mediawiki.includes.parsers.*;
public class Xomw_param_map {
	private final    Ordered_hash hash = Ordered_hash_.New_bry();
	public final    Xomw_params_frame          Frame   = new Xomw_params_frame();
	public final    Xomw_params_handler        Handler = new Xomw_params_handler();
	public final    Xomw_params_horizAlign     HorizAlign = new Xomw_params_horizAlign();
	public final    Xomw_params_vertAlign      VertAlign = new Xomw_params_vertAlign();
	public int Len() {return hash.Len();}
	public Xomw_param_itm Get_at(int i) {return (Xomw_param_itm)hash.Get_at(i);}
	public Xomw_param_itm Get_by(byte[] name) {
		return (Xomw_param_itm)hash.Get_by(name);
	}
	public Xomw_param_itm Get_by(int name_type) {
		return null;
	}
	public void Set(int type, int paramNameUid, byte[] paramBry, int paramInt) {
		switch (type) {
			case Type__frame:   Frame.Set(paramNameUid, paramBry, paramInt); break;
			case Type__handler: Handler.Set(paramNameUid, paramBry, paramInt); break;
		}
	}
	public byte[][] Keys() {
		int len = hash.Len();
		byte[][] rv = new byte[len][];
		for (int i = 0; i < len; i++) {
			rv[i] = ((Xomw_param_itm)hash.Get_at(i)).magic;
		}
		return rv;
	}
	public void Add(byte[] magic, int type_uid, byte[] name) {
		Xomw_param_itm itm = new Xomw_param_itm(magic, type_uid, name);
		hash.Add(magic, itm);
	}
	public Xomw_param_map Clone() {
		Xomw_param_map rv = new Xomw_param_map();
		int len = hash.Len();
		for (int i = 0; i < len; i++) {
			Xomw_param_itm itm = (Xomw_param_itm)hash.Get_at(i);
			rv.Add(itm.magic, itm.type_uid, itm.name);
		}
		rv.Frame.Copy_to(this.Frame);
		rv.Handler.Copy_to(this.Handler);
		return rv;
	}

	public static final int Type__horizAlign = 0, Type__vertAlign = 1, Type__frame = 2, Type__handler = 3;
}
class Xomw_param_list {
	public int type_uid;
	public byte[] type;
	public byte[][] names;

	public static Xomw_param_list New(int type_uid, String type, String... names) {
		Xomw_param_list rv = new Xomw_param_list();
		rv.type_uid = type_uid;
		rv.type = Bry_.new_u8(type);
		rv.names = Bry_.Ary(names);
		return rv;
	}
}
