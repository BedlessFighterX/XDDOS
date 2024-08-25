package XD.XDDOS.utils.packet;

import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class DefinedPacket {
   public static Charset UTF_8 = Charset.forName("UTF-8");

   public static void writeString(String s, ByteBuf buf) {
      if (s.length() > 32767) {
         throw new RuntimeException(String.format("Cannot send string longer than Short.MAX_VALUE (got %s characters)", s.length()));
      } else {
         byte[] b = s.getBytes(UTF_8);
         writeVarInt(b.length, buf);
         buf.writeBytes(b);
      }
   }

   public static void writeStringC(String s, ByteBuf buf) {
      byte[] b = s.getBytes(StandardCharsets.UTF_16);
      writeVarInt(b.length, buf);
      buf.writeBytes(b);
   }

   public static String readString(ByteBuf buf) {
      int len = readVarInt(buf);
      if (len > 32767) {
         throw new RuntimeException(String.format("Cannot receive string longer than Short.MAX_VALUE (got %s characters)", len));
      } else {
         byte[] b = new byte[len];
         buf.readBytes(b);
         return new String(b, UTF_8);
      }
   }

   public static void writeString(String s, int maxLength, ByteBuf buf) {
      if (s.length() > maxLength) {
         throw new RuntimeException(String.format("Cannot send string longer than %s (got %s characters)", maxLength, s.length()));
      } else {
         byte[] b = s.getBytes(UTF_8);
         writeVarInt(b.length, buf);
         buf.writeBytes(b);
      }
   }

   public static String readString(ByteBuf buf, int maxLength) {
      int len = readVarInt(buf);
      if (len > maxLength) {
         throw new RuntimeException(String.format("Cannot receive string longer than %s (got %s characters)", maxLength, len));
      } else {
         byte[] b = new byte[len];
         buf.readBytes(b);
         return new String(b, UTF_8);
      }
   }

   public static void writeArray(byte[] b, ByteBuf buf) {
      if (b.length > 32767) {
         throw new RuntimeException(String.format("Cannot send byte array longer than Short.MAX_VALUE (got %s bytes)", b.length));
      } else {
         writeVarInt(b.length, buf);
         buf.writeBytes(b);
      }
   }

   public static byte[] toArray(ByteBuf buf) {
      byte[] ret = new byte[buf.readableBytes()];
      buf.readBytes(ret);
      return ret;
   }

   public static byte[] readArray(ByteBuf buf) {
      return readArray(buf, buf.readableBytes());
   }

   public static byte[] readArray(ByteBuf buf, int limit) {
      int len = readVarInt(buf);
      if (len > limit) {
         throw new RuntimeException(String.format("Cannot receive byte array longer than %s (got %s bytes)", limit, len));
      } else {
         byte[] ret = new byte[len];
         buf.readBytes(ret);
         return ret;
      }
   }

   public static int[] readVarIntArray(ByteBuf buf) {
      int len = readVarInt(buf);
      int[] ret = new int[len];

      for(int i = 0; i < len; ++i) {
         ret[i] = readVarInt(buf);
      }

      return ret;
   }

   public static void writeStringArray(List<String> s, ByteBuf buf) {
      writeVarInt(s.size(), buf);
      Iterator var3 = s.iterator();

      while(var3.hasNext()) {
         String str = (String)var3.next();
         writeString(str, buf);
      }

   }

   public static List<String> readStringArray(ByteBuf buf) {
      int len = readVarInt(buf);
      List<String> ret = new ArrayList<String>(len);

      for(int i = 0; i < len; ++i) {
         ret.add(readString(buf));
      }

      return ret;
   }

   public static int readVarInt(ByteBuf input) {
      return readVarInt(input, 5);
   }

   public static int readVarInt(ByteBuf input, int maxBytes) {
      int out = 0;
      int bytes = 0;

      while(input.readableBytes() != 0) {
         byte in = input.readByte();
         out |= (in & 127) << bytes++ * 7;
         if (bytes > maxBytes) {
            throw new RuntimeException("OVERSIZED_VAR_INT_EXCEPTION");
         }

         if ((in & 128) != 128) {
            return out;
         }
      }

      throw new RuntimeException("No more bytes");
   }

   public static void writeVarInt(int value, ByteBuf output) {
      do {
         int part = value & 127;
         value >>>= 7;
         if (value != 0) {
            part |= 128;
         }

         output.writeByte(part);
      } while(value != 0);

   }

   public static int readVarShort(ByteBuf buf) {
      int low = buf.readUnsignedShort();
      int high = 0;
      if ((low & '耀') != 0) {
         low &= 32767;
         high = buf.readUnsignedByte();
      }

      return (high & 255) << 15 | low;
   }

   public static void writeVarShort(ByteBuf buf, int toWrite) {
      int low = toWrite & 32767;
      int high = (toWrite & 8355840) >> 15;
      if (high != 0) {
         low |= 32768;
      }

      buf.writeShort(low);
      if (high != 0) {
         buf.writeByte(high);
      }

   }

   public static void writeUUID(UUID value, ByteBuf output) {
      output.writeLong(value.getMostSignificantBits());
      output.writeLong(value.getLeastSignificantBits());
   }

   public static UUID readUUID(ByteBuf input) {
      return new UUID(input.readLong(), input.readLong());
   }

   public void read(ByteBuf buf) {
      throw new UnsupportedOperationException("Packet must implement read method");
   }

   public void write(ByteBuf buf) {
      throw new UnsupportedOperationException("Packet must implement write method");
   }
}
