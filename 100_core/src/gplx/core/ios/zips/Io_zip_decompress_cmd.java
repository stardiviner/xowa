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
package gplx.core.ios.zips; import gplx.*; import gplx.core.*; import gplx.core.ios.*;
import gplx.core.progs.*;
public interface Io_zip_decompress_cmd {
	String Fail_msg();
	Io_zip_decompress_cmd Make_new();
	long Checkpoint__load_by_src_fil(Io_url src_fil);
	byte Exec(gplx.core.progs.Gfo_prog_ui prog_ui, Io_url src_fil, Io_url trg_dir, List_adp trg_fils);
	void Exec_cleanup();
}
