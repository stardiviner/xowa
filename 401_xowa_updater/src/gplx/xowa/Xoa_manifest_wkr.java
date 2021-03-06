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
package gplx.xowa; import gplx.*;
import gplx.core.envs.*;
class Xoa_manifest_wkr {
	private final    Xoa_manifest_view view;
	private final    Xoa_manifest_list list = new Xoa_manifest_list();
	private Io_url manifest_url, update_root;
	private String run_xowa_cmd;
	public Xoa_manifest_wkr(Xoa_manifest_view view) {
		this.view = view;
	}
	public void Init(Io_url manifest_url, Io_url update_root) {
		// load manifest
		this.manifest_url = manifest_url;
		this.update_root = update_root;
		view.Append("loading manifest from: " + manifest_url.Raw());
		byte[] src = Io_mgr.Instance.LoadFilBry(manifest_url);

		// parse manifest
		int nl_pos = Bry_find_.Find_fwd(src, Byte_ascii.Nl);
		if (nl_pos == Bry_find_.Not_found) throw Err_.new_wo_type("could not find nl in manifest", "manifest_url", manifest_url.Raw());
		this.run_xowa_cmd = String_.new_u8(src, 0, nl_pos);
		list.Load(src, nl_pos + 1, src.length);
		
		this.Wait();
	}
	private void Wait() {
		int tries = 0;
		while (tries++ < 100) {
			gplx.core.threads.Thread_adp_.Sleep(1000);
			if (Copy_files())
				break;
			else {
				String topmost = "#error";
				if (list.Len() > 0) {
					Xoa_manifest_item item = (Xoa_manifest_item)list.Get_at(0);
					topmost = item.Src().Raw();
				}
				view.Append("waiting for XOWA to release file: " + topmost);
			}
		}
		this.On_exit();
	}
	public void On_exit() {
		// delte update root and manifest_url
		view.Append("deleting update dir: " + update_root.Raw());
		Io_mgr.Instance.DeleteDirDeep(update_root);
		Io_mgr.Instance.DeleteFil(manifest_url);
		
		view.Mark_done(run_xowa_cmd);
		view.Run_xowa();
	}
	private boolean Copy_files() {
		// loop list and copy items
		int len = list.Len();
		int idx = 0;
		for (idx = 0; idx < len; idx++) {
			Xoa_manifest_item item = (Xoa_manifest_item)list.Get_at(idx);
			try {
				Io_mgr.Instance.DeleteFil_args(item.Trg()).MissingFails_off().Exec();
				Io_mgr.Instance.MoveFil_args(item.Src(), item.Trg(), true).Exec();;
				view.Append(String_.Format("copied file: src={0} trg={1}", item.Src().Raw(), item.Trg().Raw()));
			}
			catch (Exception exc) {
				view.Append(String_.Format("failed to copy file: src={0} trg={1} err={2}", item.Src().Raw(), item.Trg().Raw(), Err_.Message_lang(exc)));
				return false;
			}
		}

		// remove completed items
		for (int i = 0; i < idx; i++) {
			list.Del_at(0);
		}
		return true;
	}
}
