package com.li.tools.utils.rsa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

//对开头是中文字段和长度过长不能解密
public class RSAUtils2 {
	public static final char[] CS = {'1','2','3','4','5','6','7','8','9','0'}; 
	public static final byte[] BS = {0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,0,1,2,3,4,5,6,7,8,9}; 
	public static final String CODE = "utf-8";
	/**
	 * 
	 * create:2016-9-5
	 * author:lijuntao
	 * 只能加密115个字节：对加密的字符串加密时，为先转为字节，字节限定125个
	 */
	public static String rsaEncrypt(String str,RSAPublicKey publicKey) throws RSAEncryptException{
		if(str==null || str.length()==0 || publicKey ==null)
			return null;
		System.out.println("加密字符串长度："+str.length()+"字符串:"+str);
		byte[] bs = null;
		try {
			bs = str.getBytes(CODE);
			System.out.println("加密字节数组长度："+bs.length+"字节数组:"+Arrays.toString(bs));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		char[] cs = bytesToChars(bs);
		/*if(cs.length>127){
			throw new RSAEncryptException("加密的数据长度过长："+cs.length+",长度应为：127以内");
		}*/
		System.out.println("加密的数据长度过长："+cs.length+",长度应为：127以内");
		System.out.println("加密字符数组长度："+cs.length+"数组:"+Arrays.toString(cs));
		BigInteger b = new BigInteger(new String(cs));
		System.out.println("加密长整数长度："+ b.toString().length()+"长整数:"+b.toString());
		BigInteger e = publicKey.getPublicExponent();  
        BigInteger m = publicKey.getModulus(); 
        BigInteger result = b.modPow(e, m);
        System.out.println("加密后长整数："+ result.toString());
		return result.toString();
	}
	public static String rsaDecrypt(String str,RSAPrivateKey privateKey){
		if(str==null || str.length()==0 || privateKey ==null)
			return null;
		System.out.println("解密字符串："+str);
		BigInteger b = new BigInteger(str);
		System.out.println("解密长整数："+b.toString());
		BigInteger e = privateKey.getPrivateExponent();  
        BigInteger m = privateKey.getModulus();
        BigInteger result = b.modPow(e, m);
        System.out.println("解密后长整数："+result.toString());
        char[] cs = result.toString().toCharArray();
        System.out.println("解密后字符数组："+Arrays.toString(cs));
        byte[] bs = charsToBytes(cs);
        System.out.println("解密后字节数组："+Arrays.toString(bs));
        String str2 = null;
        try {
			str2 = new String(bs,CODE);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        System.out.println("解密后字符串："+str2);
        return str2;
	}
	
	public static void main(String[] args){
		KeyPairGenerator kpg = null;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}  
		kpg.initialize(1024);  
		KeyPair kp = kpg.genKeyPair();  
		RSAPublicKey pbkey = (RSAPublicKey) kp.getPublic();  
		RSAPrivateKey prkey = (RSAPrivateKey) kp.getPrivate();
		
        String s = "李eliFLKASFLLKAdfhcqwehelgwflihheliFLKASFLLKAdfhcqwehelgwflihheliFLKASFLLKAdfhcqwehelgwflihheliFLKASFLLKAdfhc11111";
        String encrypt2 = null;
		try {
			encrypt2 = rsaEncrypt(s,pbkey);
		} catch (RSAEncryptException e1) {
			e1.printStackTrace();
		}
        String decrypt2 = rsaDecrypt(encrypt2,prkey);
        int size = 0;
        for(int i=0;i<0;i++){
        	String str = getRandomStr(70);
        	String encrypt = "";
			try {
				encrypt = rsaEncrypt(str,pbkey);
			} catch (RSAEncryptException e) {
				e.printStackTrace();
			}
            String decrypt = rsaDecrypt(encrypt,prkey);
            if(str.equals(decrypt)){
            	size++;
            }
        }
        System.out.println("size:"+size);
	}
	
	public static String getRandomStr(int size){
		if(size<0 || size>100000)
			size=100;
		Random random = new Random();
		char[] cs = new char[size];
		for(int j=0;j<size;j++){
			int i = random.nextInt(RandomTable.lower_case_letters.length);
			cs[j] = RandomTable.lower_case_letters[i];
		}
		return new String(cs);
	}
	
	public static int[] BytesToPositiveInts(byte[] bs){
		if(bs == null)
			return null;
		int[] is = new int[bs.length];
		for(int i=0;i<bs.length;i++){
			is[i] = bs[i] & 0xff;
		}
		return is;
	}
	public static byte[] positiveIntsToBytes(int[] is){
		if(is == null)
			return null;
		byte[] bs = new byte[is.length];
		for(int i=0;i<is.length;i++){
			bs[i] = (byte) is[i];
		}
		return bs;
	}
	@Test
	public void test(){
	    char[] s = {'a','b'};
	    BigInteger a = new BigInteger("12341412312123123");
	    System.out.println(Arrays.toString(a.toByteArray()));
	    a.toString();
	}
	
	public static char[] bytesToChars(byte[] bs){
		if(bs==null || bs.length == 0)
			return null;
		char[] cs = null;
		int leng = bs.length;
		int j = leng / 3;
		int k = leng % 3;
		if(k == 0)
			cs = new char[j*8];
		else if(k == 1)
			cs = new char[j*8+3];
		else if(k == 2)
			cs = new char[j*8+6];
		for(int m=0;m<j;m++){
			int begin = m*3;
			int bbegin = m*8;
			cs[bbegin]= CS[ bs[begin]>>5 & 0x7];
			cs[bbegin+1]= CS[ bs[begin]>>2 & 0x7 ];
			cs[bbegin+2]= CS[ (bs[begin]&0x3)*2 + (bs[begin+1]>>7&0x1)];
			cs[bbegin+3]= CS[ bs[begin+1]>>4 & 0x7];
			cs[bbegin+4]= CS[ bs[begin+1]>>1 & 0x7];
			cs[bbegin+5]= CS[ (bs[begin+1]&0x1)*4 + (bs[begin+2]>>6&0x3)];
			cs[bbegin+6]= CS[ bs[begin+2]>>3 & 0x7];
			cs[bbegin+7]= CS[ bs[begin+2] & 0x7];
		}
		if(k!=0){
			cs[j*8] = CS[ bs[j*3]>>5 & 0x7];
			cs[j*8+1] = CS[ bs[j*3]>>2 & 0x7];
			cs[j*8+2] = CS[ bs[j*3] & 0x3];
		    if(k==2){
				cs[j*8+3] = CS[ bs[j*3+1]>>5 & 0x7];
				cs[j*8+4] = CS[ bs[j*3+1]>>2 & 0x7];
				cs[j*8+5] = CS[ bs[j*3+1] & 0x3];
		    }
		}
		return cs;
	}
	
	public static byte[] charsToBytes(char[] cs){
		if(cs==null||cs.length == 0)
			return null;
		byte[] bs = null;
		int leng = cs.length;
		int j = leng / 8;
		int k = leng % 8;
		if(k==0)
			bs = new byte[j*3];
		else if(k==3)
			bs = new byte[j*3+1];
		else if(k==6)
			bs = new byte[j*3+2];
		else{
			System.out.println("格式不对");
			throw new RuntimeException("字符数组的长度不对");
		}
		/*for(int m=0;m<j;m++){
			int cbegin = m*8;
			int bbegin = m*3;
			byte v1 = (byte) (BS[cs[cbegin]]*0x20);
			byte v2 = (byte) (BS[cs[cbegin+1]]*0x4);
			byte v3 = (byte) ((BS[cs[cbegin+2]]>>1));
			byte v4 = (byte) ((BS[cs[cbegin+2]]&0x1)*0x80);
			byte v5 = (byte) (BS[cs[cbegin+3]]*0x10);
			byte v6 = (byte) ((BS[cs[cbegin+4]]*2) + (BS[cs[cbegin+5]]>>2&0x1));
			byte v7 = (byte) ((BS[cs[cbegin+5]]&0x3)*0x40);
			byte v8 = (byte) (BS[cs[cbegin+6]]*0x8);
			byte v9 = (byte) BS[cs[cbegin+7]];
			byte v10 = (byte) ((BS[cs[cbegin+5]]&0x3)*0x40 + BS[cs[cbegin+6]]*0x8 + BS[cs[cbegin+7]]);
			int i = 0;
		}*/
		for(int m=0;m<j;m++){
			int cbegin = m*8;
			int bbegin = m*3;
			bs[bbegin] = (byte) ((BS[cs[cbegin]]*0x20) + (BS[cs[cbegin+1]]*0x4) + (BS[cs[cbegin+2]]>>1));
			bs[bbegin + 1] = (byte) ((BS[cs[cbegin+2]]&0x1)*0x80 + BS[cs[cbegin+3]]*0x10 + ((BS[cs[cbegin+4]]*2) + (BS[cs[cbegin+5]]>>2&0x1)));
			bs[bbegin + 2] =  (byte) ((BS[cs[cbegin+5]]&0x3)*0x40 + BS[cs[cbegin+6]]*0x8 + BS[cs[cbegin+7]]);
		}
		if(k!=0){
			bs[3*j] = (byte) (BS[cs[8*j]]*0x20 + BS[cs[8*j+1]]*0x4 + (BS[cs[8*j+2]]&0x3)*0x1);
			if(k==6)
				bs[3*j + 1] = (byte) (BS[cs[8*j+3]]*0x20 + BS[cs[8*j+4]]*0x4 + (BS[cs[8*j+5]]&0x3)*0x1);
		}
		
		
		return bs;
	}
	@Test
	public void hh(){
		byte[] bs3 = {45,47,45,56,34,10,12,34,56,34,45,23,4,46,35,-6,-1};
		byte[] bs = new byte[256*3];
		for(int i=-128;i<128;i++){
			bs[3*(i+128)] = (byte) i;
			bs[3*(i+128)+1] = (byte) i;
			bs[3*(i+128)+2] = (byte) i;
		}
		System.out.println(Arrays.toString(bs));
		
		char[] cs2 = bytesToChars(bs);
		String string = Arrays.toString(cs2);
		System.out.println(string);
		byte[] bs2 = charsToBytes(cs2);
		get(bs2);
		String string2 = Arrays.toString(bs2);
		System.out.println(string2);
		char i = '2';
		byte b = (byte) i;
		System.out.println(b);
		System.out.println(BS[i]);
		System.out.println(BS.length);
		System.out.println(0x20);
	}
	
	public void get(byte[] bs){
		if(bs==null || bs.length == 0)
			return;
		byte[] bs2 = new byte[bs.length/3];
		int j = 0;
		for(int i=0;i<bs.length/3;i++){
			if(bs[3*i] == bs[3*i+1] && bs[3*i+1] == bs[3*i+2]){}
			else{
				bs2[j++] = bs[i];
			}
		}
		System.out.println("get:"+Arrays.toString(bs2));
	}
	
	public void printf(String s){
		char[] cs2 = s.toCharArray();
		String s1 = "";
		int i = 0;
		for(char c: cs2){
			if(i!=0)
				s1 +=",";
			i++;
			s1 =  s1 + "'"+c+"'";
		}
		System.out.println(s1);
	}
	public void printf(int size){
		if(size<0)
			size = 0;
		String s1 = "";
		int j = 0;
		for(int i=1;i<=size;i++){
			if(j!=0)
				s1 +=",";
			j++;
			s1 =  s1 + ""+i+"";
		}
		s1 = s1.replaceAll("？", "");
		s1 = s1.replaceAll("？", "");
		s1 = s1.replaceAll("|", "");
		System.out.println(s1);
	}
	@Test
	public void te(){
		printf("亍彳邒卬殳毌邘戋圢氕伋仝氿汈氾忉宄訏讱玐㺩扞圲圫芏芃朳朸邨吒吖屼屾辿钆仳㲻伣伈癿甪邠犴冱㡯邡闫隄蠚䜣讻詝孖㚤紃纩玗玒玔玓玘㺭玚塸坜坉埨坋扺㧑毐芰芣苊苉芘䒜芴芠芤杕杙杄杋杧杩尪尨轪軏坒芈旴旵呙㕮岍㠣岠岜呇冏觃岙伾㑇伭佖佁肜飏狃疕闶汧汫沄漙沘浿汭㳇沕沇忮忳忺諓祃诇邲诎诐屃彄岊阽䢺阼妌妧媁妘姂纮驲馼纻紞駃纼玤玞珼瑽玪玱玟邽邿坫坥坰坬坽弆耵䢼苼茋苧苾苠枅㭎枘枍厔矼矻呠檿仌J昇昄昒昈甽咉咇咍岵岽岨岞峂㟃囷釴钐钔钖䢾牥佴垈侁侹佸佺隹㑊侂佽侘㑮㑎郈郐郃攽肭肸肷狉狝㹣颹饳忞於並炌炆泙沺泂泜泃泇怊峃穸祋詷詪鄩麖弨陑隑陎隮卺乸㚰㚴妭妰姈嬣妼娙迳叕駓驵駉䌹驺䮄绋绐砉耔㛃玵玶瓐珇珅珃瓅玽珋玸玹珌玿㺹韨垚垯垙垲㧥埏垍耇垎垴垟垞挓垏拶荖荁荙荛茈茽荄茺蔄荓茳茛荭㭕柷柃柊枹栐柖郙郚剅䴓迺䣅厖砆砑砄耏奓䶮轵轷轹轺昺睍昽盷咡咺昳昣哒昤昫昡咥昪虷虸哃峘峏峛峗峧帡钘鈇鍏钜鋹釿錀钪钬钭矧秬俫舁俜俙俍垕衎㣝舣弇侴鸧䏡胠胈胩胣朏飐䫾訄饻庤疢炣炟㶲洭洘洓洏洿㳚泚浉洸洑洢洈洚洺洨浐㳘洴洣恔宬窀扂謰袆祏祐祕叚陧陞娀姞姱娍姯嬅姤姶姽枲绖骃絪駪綎綖彖骉恝珪珬珛珹玼珖珚勣珽珦珘珨珫珒璕珢珕珝埗垾垺埆垿埌埇莰茝䓣鄀莶莝䓂莙栻桠梜桄梠栴梴栒栘酎酏頍砵砠砫砬硁恧翃郪辀辁剕赀哢晅晊唝哳唭坳蕰晖畖蚄蚆鄳帱崁峬峿輋崄帨崀赆鉥钷鑪鉮鉊鉧眚甡笫倻倴脩倮倕倞僤倓倧衃虒舭舯舥瓞鬯鸰脎朓胲虓鱽狴峱狻眢餗勍痄疰痃竘䍨䍩羖羓桊敉烠烔烶烻烅烑燖涍浡浬涄涢涐浰浟浛浼浲浵浫涘悈悃悢礐宧窅窊窎扅扆袪袗袯祧隺堲疍陴䧑烝娪㛒娏婡嬐砮㛚哿翀翂剟駼絺绤骍綄駺䂮琎珸㻉珵琄㻌琈琀琂珶珺嫢掭堎堐埼掎埫堌晢墠掞埪壸㙍聍菝萚菥莿䓫勚䓬萆菂菍菼萣䓨菉䓛梼梽桲梾桯梣梌桹敔厣硔䃮硙硃硚硊硍勔龁祡砦逴唪晘翈㫰晙畤頔趼跂蛃蚲蝀蚺啴䎃崧崟崞崒崌崡铏銈鉷铕鐽铖铘铚铞铥铦铴牻牿稆䇞笱笣笯偡鸺偭偲偁鄅偓徛衒舳舲鸼悆鄃瓻䝙脶脞脟䏲鱾猇猊猄觖鄮庱庼庳痓䴔竫堃羝羕燽焆烿烺焌渶淏漍淟淜淴淯湴涴璗㥄惛惔悰惙寁逭諲諴袼裈祲諟謏谞艴弸弶隤隃嫧婞娵婼媖婳婍婌婋婫婩㛥婇婤婘婠娽娺綪綝騑騊绹綡綧骕騄絜珷㻓瓄琲琡㻒㻔㻑琟琗琔琭堾堼揕㙘堧喆揾堨塅堠絷塿堟葜惎萳葙靬葴蒇蒈鄚蒉蓇萩蒐葰葎葹鄑蒎葖蒄萹棤棽棫椓𣓉椢椑櫍鹀椆棓棬棪椀楗盚鵏甦䣓酦觌奡皕硪欹詟輗辌棐龂齘黹牚睎𥆧瞷晫晪晱𧿹蛑畯斝喤崶嵁嵽崾嵅崿嵚翙[岂+页]圌圐䞍赑淼赒䥑铹𨧀铽𨱇鋗锊锍锎鋐锓犇颋稌䅋筀筘筜筥筅傃傉翛傒傕舾畬殽蠲犇N䐃䏽腙腒頠魪鲃猰l㺄馉凓鄗廞廋廆鄌粢遆旐闉焞燀欻𣸣溚溁湝渱渰湓㴔渟湙溠渼溇湣湑溞愐愃敩甯棨扊裣祼隞婻媙媔媼q㛱㛹媓媂媄媥媃毵矞騞騠缊缐骙瑃㻡瑓㻟瑅瑆䴖瑖瑝瑔瑀𤧛瑳㻠瑏瑂嶅瑈瑑遘䪞髢塥堽赪摛塝搒搌蒱蒨蓏蔀蓢蓂蒻蓣椹楪榃榅楒楞楩榇椸楙歅醲碃碏[石+肯]碈䃅硿𩐁鄠辒輶輮龆觜𨝘䣘暕鹍噁㬈㬊暅跱𨁂蜐蜎嵲㡗赗骱锖錤锘锳锧錋锪錞锫锬𨨏稑稙䅟篢筻筼筶筤傺鹎僇艅艉谼貆腽腨腯鲉鲊鲌䲟鮈鮀鲏雊猺飔觟𦝼馌裛廒瘀瘅鄘鹒鄜麀鄣阘闑煁爃煃煴煋煟煓滠溍溹滆滉溦溵漷滧滘滍愭慥慆塱襀裼禋禔禘禒谫鹔頵愍嫃嫄媱戤勠戣騵騱耤瑧璊瑨瑱璼瑷瑢斠摏墈墐墘摴銎𡐓墚撖[土+翏]靽鞁蔌蔈蓰蔹蔊嘏榰榑槚𣗋槜榍樋疐鶠酺酾酲酴䃎[石+览]碨𥔲碹碥劂鮆夥瞍鹖㬎跽蜾幖嶍圙𨱏锺锼锽䤼鍭锾锿镃镄镅馝箨箖劄𥮾僬僦僔躴僎槃㙦賸鲒鲕鰤鲖鲘鲙鮡鮠𩽾夐獍飗鸑凘廑廙瘗瘥瘕𣄎鄫熇漹潆潩漼漴㽏漈漋漻慬窬窭㮾譓褕禛禚隩嫤嫕嫭嫜嫪縯㻬麹璆漦叇墣墦墡劐薁蕰蔃鼒槱鹝磏磉殣戨悤習聲罆脮谯p踣䗖蝘蝲蝤噇噂噀罶嶲嶓㠇嶟嶒镆镈镋镎鎓镕稹篊儇皞皛艎艏鹟𩾃鲦鲪鲬橥觭鹠鹡糇糈翦鹢鹣熛潖潵㵐澛瑬潾潏憭憕鶱戭褯禤𥛚譞㜤嫽㜣嬁遹驎𤩲璥㻸瓓璲㻵𤩄璒憙擐鄹薳鞔黇蘋𨞼蕗薐薢蕹橞橑橦醑瑿觱磡𥕢磜豮䡵齮齯鹾虤暿曔暻曌曈㬚蹅踶䗛螗𧎥疁㠓幪巘嶦鏏𨱑䥕馞穄篚䈪篯簉鼽衠盦螣縢鲭鲯鲰鲺鲹饘亸癀瘭鷟羱糒燋熻燊燚燏濩濛濋澪澽澴澭澼𢣏憷憺懔黉嬛嬚鹨翯繶璱瓁𤩽㻿璬璮髽擿盩薿薸檑櫆檞醨繄磹磻瞫瞵蹐蟏㘎𨭎镤𨭆鐇镥镨鏻𨱔鐩鐍矰穙穜穟簕簃簏儦魋斶艚鷭谿臁鯻鲾鰊鳁鳂䱸鳈鳉獯䚦䗪馘襕襚鱀螱甓嬬嬥𦈡纁𤪌瓀瓋瓍璻釐鬶䰂爇鞳鞮虉藟藦藨鹲檽檫黡礞礌𥖨㬤蹢蹜蟫䗴嚚髃𩪁镮镱酂馧簠簝簰𥳘鼫鼩皦臑䲢鳑鳒鹱鹯癗𦒍旞翷冁䎖瀔瀍瀌襜㜴纆瓃𣂏嚭酄㰀鬷醭蹯𧒽蠋翾鳘儳儴鼗鰶𩾌鳚鳛麑麖蠃爅彟嬿瓌鬒蘶蘘欂醵颥甗𨟠巇酅髎犨鱚黁𨭉㸌爔瀱瀹瀼瀵襫孅驩骦纕耰𤫉瓖鬘趯礳齼𣌓罍鼱鳠鳡爟爚爙灈韂糵蘼礵鹴㬬躔皭龢鳤亹䂂籥鼷鱲玃醾齇觿亍彳邒卬殳𠙶毌邘戋圢氕伋仝𨙮氿汈𣱼氾忉宄訏讱玐㺩扞圲圫芏芃朳朸𨙶𨙸邨吒吖屼屾辿钆仳㲻伣伈癿甪邠犴冱㡯邡闫隄蠚𢖳䜣讻詝孖𨸝㚤紃纩玗玒玔玓玘㺭玚塸坜坉埨坋扺㧑毐𦔮芰芣苊苉芘䒜芴芠[艹+为]芤杕杙杄杋杧杩尪尨轪軏坒芈𠧚旴旵呙㕮岍㠣岠岜呇冏觃岙伾㑇伭佖佁肜飏狃𨚔疕闶汧汫𣲘𣲗沄漙沘浿汭㳇沕沇忮忳忺諓祃诇邲诎诐屃彄𨚓岊阽䢺阼妌妧媁妘姂𨚕纮驲馼纻紞駃纼玤玞珼瑽玪玱玟邽邿坫坥坰坬坽弆耵䢼𦭜苼茋苧苾苠枅㭎枘枍厔矼矻呠檿仌J昇昄昒昈甽咉咇咍岵岽岨岞峂㟃囷釴钐钔钖䢾牥佴垈侁侹佸佺隹㑊侂佽侘㑮㑎郈郐𦔯郃攽肭肸肷狉狝㹣颹饳忞於並炌炆泙沺泂泜泃泇怊峃穸祋詷詪鄩麖|弨陑隑陎隮卺乸㚰㚴妭𡛟妰姈嬣妼娙迳叕駓驵𩧬駉䌹驺䮄绋绐砉耔㛃玵𤤒玶瓐珇珅珃瓅玽珋玸玹珌玿㺹韨垚垯垙垲㧥埏垍耇[土+𡕘]垎垴垟垞挓垏拶荖荁荙荛茈茽荄茺蔄荓茳𦰡茛荭㭕柷柃柊枹栐柖郙郚剅䴓迺䣅厖砆𥐰砑砄耏奓䶮𠡠轵轷轹轺昺睍昽盷咡咺昳昣哒昤昫昡咥昪虷虸哃峘峏峛𡷫[山+曲]峗峧帡钘鈇鍏钜鋹釿錀钪钬钭矧秬俫舁俜俙俍垕衎㣝舣弇侴鸧䏡胠𦙶胈胩胣朏飐䫾訄饻庤疢炣炟㶲洭洘洓洏洿㳚泚浉洸洑洢洈洚洺洨浐㳘洴洣恔宬窀扂謰袆祏祐祕叚陧陞娀姞姱娍姯嬅姤姶姽枲绖骃絪駪綎綖彖骉恝珪珬珛珹玼珖𤤺珚勣珽珦珘珨𤤸珫珒璕珢珕珝𡑍埗垾垺埆垿埌埇莰茝䓣鄀𦯬莶莝䓂莙栻桠梜桄梠栴梴栒栘酎酏頍砵砠砫砬硁恧翃郪𨐈辀辁[牙+合]剕赀哢晅晊唝哳唭坳蕰|晖畖蚄蚆鄳帱崁峬峿輋崄帨崀赆鉥钷鑪鉮鉊鉧眚甡𤙔笫倻倴脩倮倕倞僤倓倧𨈓衃虒舭舯舥瓞𨛭鬯鸰脎朓胲虓鱽狴峱狻眢餗勍痄疰痃竘䍨䍩羖羓桊敉烠烔烶烻烅烑燖涍浡浬涄涢涐浰浟浛浼浲浵浫涘悈悃悢礐宧窅窊窎扅扆袪袗袯祧隺堲疍𨺙陴䧑烝娪㛒娏婡𡝐嬐砮㛚哿翀翂剟駼絺绤骍綄駺䂮琎珸㻉珵琄㻌琈琀琂珶珺嫢掭堎堐埼掎埫堌晢墠掞埪壸㙍聍菝萚菥莿䓫勚䓬萆菂菍菼萣䓨菉䓛梼梽桲梾桯梣梌桹敔厣硔䃮硙硃硚硊硍勔龁祡砦逴唪𣆳晘翈㫰𣆲晙畤頔趼跂蛃蚲蝀蚺啴䎃𡹇崧崟崞崒崌崡铏銈鉷铕鐽铖铘铚铞铥铦铴牻牿稆䇞笱笣笯偡鸺偭偲偁鄅偓𢔁徛衒舳舲鸼悆鄃瓻䝙脶脞脟䏲鱾猇猊猄觖鄮庱庼庳痓䴔竫堃羝羕燽焆烿烺焌渶淏漍淟淜淴淯湴涴璗㥄惛惔悰惙寁逭諲諴袼裈祲諟謏谞艴弸弶隤隃嫧婞娵婼媖婳婍婌婋婫婩㛥婇婤婘婠娽娺綪綝騑騊绹綡綧骕騄絜珷㻓瓄琲琡㻒㻔㻑琟𤥿琗𤥽琔琭堾堼揕㙘堧喆揾堨塅𢯺堠絷塿𡎚堟葜惎萳葙靬葴蒇蒈鄚蒉蓇萩蒐葰葎葹鄑蒎葖蒄萹棤棽棫椓𣓉椢椑櫍鹀椆棓棬棪椀楗盚鵏甦䣓酦觌奡皕硪欹詟輗辌棐龂齘黹牚睎𥆧瞷晫晪晱𧿹蛑畯斝喤崶嵁嵽崾嵅崿嵚翙[岂+页]圌圐䞍赑淼赒䥑铹𨧀铽𨱇鋗锊锍锎鋐锓犇颋稌䅋筀筘筜筥筅傃傉翛傒傕舾畬殽蠲犇N䐃䏽腙腒頠魪鲃猰l㺄馉凓鄗廞廋廆鄌粢遆旐闉焞燀欻𣸣溚溁湝渱渰湓㴔渟湙溠渼溇湣湑溞愐愃敩甯棨扊裣祼隞婻媙媔媼q㛱㛹媓媂媄媥媃毵矞騞騠缊缐骙瑃㻡瑓㻟瑅瑆䴖瑖瑝瑔瑀𤧛瑳㻠瑏瑂嶅瑈瑑遘䪞髢塥堽赪摛塝搒搌蒱蒨蓏蔀蓢蓂蒻蓣椹楪榃榅楒楞楩榇椸楙歅醲碃碏[石+肯]碈䃅硿𩐁鄠辒輶輮龆觜𨝘䣘暕鹍噁㬈㬊暅跱𨁂蜐蜎嵲㡗赗骱锖錤锘锳锧錋锪錞锫锬𨨏稑稙䅟篢筻筼筶筤傺鹎僇艅艉谼貆腽腨腯鲉鲊鲌䲟鮈鮀鲏雊猺飔觟𦝼馌裛廒瘀瘅鄘鹒鄜麀鄣阘闑煁爃煃煴煋煟煓滠溍溹滆滉溦溵漷滧滘滍愭慥慆塱襀裼禋禔禘禒谫鹔頵愍嫃嫄媱戤勠戣騵騱耤瑧璊瑨瑱璼瑷瑢斠摏墈墐墘摴銎𡐓墚撖[土+翏]靽鞁蔌蔈蓰蔹蔊嘏榰榑槚𣗋槜榍樋疐鶠酺酾酲酴䃎[石+览]碨𥔲碹碥劂鮆夥瞍鹖㬎跽蜾幖嶍圙𨱏锺锼锽䤼鍭锾锿镃镄镅馝箨箖劄𥮾僬僦僔躴僎槃㙦賸鲒鲕鰤鲖鲘鲙鮡鮠𩽾夐獍飗鸑凘廑廙瘗瘥瘕𣄎鄫熇漹潆潩漼漴㽏漈漋漻慬窬窭㮾譓褕禛禚隩嫤嫕嫭嫜嫪縯㻬麹璆漦叇墣墦墡劐薁蕰蔃鼒槱鹝磏磉殣戨悤習聲罆脮谯p踣䗖蝘蝲蝤噇噂噀罶嶲嶓㠇嶟嶒镆镈镋镎鎓镕稹篊儇皞皛艎艏鹟𩾃鲦鲪鲬橥觭鹠鹡糇糈翦鹢鹣熛潖潵㵐澛瑬潾潏憭憕鶱戭褯禤𥛚譞㜤嫽㜣嬁遹驎𤩲璥㻸瓓璲㻵𤩄璒憙擐鄹薳鞔黇蘋𨞼蕗薐薢蕹橞橑橦醑瑿觱磡𥕢磜豮䡵齮齯鹾虤暿曔暻曌曈㬚蹅踶䗛螗𧎥疁㠓幪巘嶦鏏𨱑䥕馞穄篚䈪篯簉鼽衠盦螣縢鲭鲯鲰鲺鲹饘亸癀瘭鷟羱糒燋熻燊燚燏濩濛濋澪澽澴澭澼𢣏憷憺懔黉嬛嬚鹨翯繶璱瓁𤩽㻿璬璮髽擿盩薿薸檑櫆檞醨繄磹磻瞫瞵蹐蟏㘎𨭎镤𨭆鐇镥镨鏻𨱔鐩鐍矰穙穜穟簕簃簏儦魋斶艚鷭谿臁鯻鲾鰊鳁鳂䱸鳈鳉獯䚦䗪馘襕襚鱀螱甓嬬嬥𦈡纁𤪌瓀瓋瓍璻釐鬶䰂爇鞳鞮虉藟藦藨鹲檽檫黡礞礌𥖨㬤蹢蹜蟫䗴嚚髃𩪁镮镱酂馧簠簝簰𥳘鼫鼩皦臑䲢鳑鳒鹱鹯癗𦒍旞翷冁䎖瀔瀍瀌襜㜴纆瓃𣂏嚭酄㰀鬷醭蹯𧒽蠋翾鳘儳儴鼗鰶𩾌鳚鳛麑麖蠃爅彟嬿瓌鬒蘶蘘欂醵颥甗𨟠巇酅髎犨鱚黁𨭉㸌爔瀱瀹瀼瀵襫孅驩骦纕耰𤫉瓖鬘趯礳齼𣌓罍鼱鳠鳡爟爚爙灈韂糵蘼礵鹴㬬躔皭龢鳤亹䂂籥鼷鱲玃醾齇觿");

	}
}
