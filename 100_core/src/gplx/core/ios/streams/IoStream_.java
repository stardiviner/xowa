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
package gplx.core.ios.streams; import gplx.*; import gplx.core.*; import gplx.core.ios.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
public class IoStream_ {
	public static final    IoStream Null = new IoStream_null();
	public static IoStream				mem_txt_(Io_url url, String v)	{return IoStream_mem.rdr_txt_(url, v);}
	public static IoStream				ary_(byte[] v)					{return IoStream_mem.rdr_ary_(Io_url_.Empty, v);}
	public static final byte Mode_rdr = 0, Mode_wtr_create = 1, Mode_wtr_append = 2, Mode_wtr_update = 3;
	public static IoStream				stream_rdr_()					{return new IoStream_stream_rdr();}
	public static IoStream				stream_input_(Io_url url)		{return new IoStream_stream_rdr().UnderRdr_(input_stream_(url));}
	public static Object				input_stream_(Io_url url)		{
				try {
			return new java.io.FileInputStream(url.Raw());
		} catch (Exception e) {throw Err_.new_wo_type("file not found", "url", url.Raw());}
			}
}
class IoStream_null implements IoStream {
	public Object UnderRdr() {return null;}
	public Io_url Url() {return Io_url_.Empty;}
	public long Pos() {return -1;}
	public long Len() {return -1;}
	public int ReadAry(byte[] array) {return -1;}
	public int Read(byte[] array, int offset, int count) {return -1;}
	public long Seek(long pos) {return -1;}
	public void WriteAry(byte[] ary) {}
	public void Write(byte[] array, int offset, int count) {}
	public void Transfer(IoStream trg, int bufferLength) {}
	public void Flush() {}
	public void Write_and_flush(byte[] bry, int bgn, int end) {}
	public void Rls() {}
}
/*
NOTE_1:stream mode
my understanding of mode
rw: read/write async?
rws: read/write sync; write content + metadata changes
rwd: read/write sync; write content
*/
//#}