package helvidios.cp.ch4.traversal;

import java.util.*;
import static java.lang.Math.*;

public class _1229_SubDictionary{
	public static void main(String[] args){
		String data = "5\r\naue oizer piqoi oizer\r\ndoy oizer hweqlo hweqlo\r\nhweqlo piqoi aue\r\noizer piqoi\r\npiqoi aue aue\r\n0\r\n";
		String data2 = "13\r\nyzkg vcjqig \r\nwsd w lzjshhp omol \r\nw wsd urhfyb atde lzjshhp unqqc urhfyb \r\nurhfyb wsd vcjqig nupe nupe \r\nlzjshhp wsd gkh ygjd wsd ygjd \r\natde qyy vcjqig omol urhfyb ygjd \r\nnupe wsd atde \r\nvcjqig unqqc unqqc qyy wsd urhfyb yzkg \r\nygjd lzjshhp omol atde lzjshhp wsd nupe \r\nqyy wsd omol atde w \r\ngkh nupe unqqc urhfyb vcjqig lzjshhp atde \r\nunqqc ygjd lzjshhp \r\nomol vcjqig \r\n11\r\neuzrg ilnt q lfmlpj \r\nvcs lfmlpj \r\naielg xacto t \r\nq vzkpwai ilnt \r\nvzkpwai ldgny \r\nxacto vzkpwai \r\nzxfh q euzrg t \r\nilnt xacto \r\nlfmlpj q vzkpwai \r\nldgny zxfh lfmlpj aielg \r\nt euzrg xacto euzrg zxfh \r\n6\r\ngaxw cm kx ovxnwit \r\novxnwit gaxw gaxw \r\nzfpvtiy gaxw bch \r\ncm kx zfpvtiy zfpvtiy \r\nbch gaxw gaxw \r\nkx ovxnwit bch gaxw \r\n3\r\nh vl \r\nvl jdqig \r\njdqig vl \r\n5\r\napa tvrrct \r\nfir lyaj eaxpf \r\ntvrrct apa eaxpf \r\nlyaj tvrrct \r\neaxpf apa \r\n16\r\nzerjobh inwppwu njbc \r\nxamjdn idzmr sxdc xxh \r\nnjbc pe xxh pe \r\nidzmr hb ya zerjobh xamjdn ij xxh sobk g \r\nkwoewkd hb gpp \r\nsxdc g pe inwppwu njbc xxh \r\ngpp inwppwu \r\nya xamjdn zerjobh ij idzmr inwppwu gpp gpp \r\npe g sobk kwoewkd ya idzmr xamjdn sobk g \r\nhb xamjdn g kwoewkd sxdc xamjdn \r\ninwppwu gpp kwoewkd pe idzmr ij xamjdn g \r\nz sxdc sxdc g ij xamjdn \r\nsobk zerjobh inwppwu ij zerjobh idzmr \r\ng njbc kwoewkd ya njbc ya sobk sxdc inwppwu \r\nxxh kwoewkd gpp g z gpp \r\nij xamjdn gpp inwppwu inwppwu inwppwu \r\n21\r\nydenvz q dwvfcfk q ajisxdk gpi dwvfcfk gecbyf zrml \r\nzrml ggvz khdjq n \r\najisxdk zrml ufwczaf n ukdrcnj dwvfcfk mwe ufwczaf rlgmvfx sayif \r\nukdrcnj g \r\nggvz mbapnqs gpi mwe rlgmvfx \r\np n ajisxdk khdjq snv mwe zrml mbapnqs \r\nkhdjq sqrkihw snv sayif gpi ajisxdk q mwe gecbyf dwvfcfk \r\ngpi mbapnqs dwvfcfk b dwvfcfk rlgmvfx \r\nb rlgmvfx g rlgmvfx ydenvz sqrkihw \r\ngecbyf n p g ufwczaf ggvz sqrkihw ydenvz snv ajisxdk \r\nsayif ajisxdk gpi gecbyf n gpi mwe ufwczaf rlgmvfx ydenvz mwe \r\nq snv khdjq ukdrcnj b ydenvz g \r\nmwe dwvfcfk dwvfcfk sayif ggvz ufwczaf gpi \r\nmbapnqs ydenvz \r\ndwvfcfk rlgmvfx n zrml rlgmvfx \r\ng gpi \r\nrlgmvfx g p sayif ydenvz g zrml \r\nufwczaf sqrkihw sayif p gpi mbapnqs p \r\nsqrkihw gpi b sayif gpi n mwe ajisxdk mbapnqs \r\nsnv n g ggvz mbapnqs rlgmvfx sayif zrml q \r\nn snv p sayif mbapnqs ukdrcnj sayif ufwczaf sayif sayif mwe \r\n0\r\n";
		String data3 = "100\r\nubghztqgvtenc prxdpnwyaamtb \r\nbtxbiiajcsjlp goqotulirejot lteqzvhspkijh \r\noactcxkkhzknr mlfjeeclhjghq \r\nqmgveexsmxljo cqdlmpkzqopcg bgpckbjshokeg fwsugvvnmizit pzltzvinsbvsf \r\ncxbtstcuiqvqo nyhfqbpvuwmwj vdmulrwscqhau acdvznnlaynbz tcgdnnvqgkfgh \r\npchvcgicjqibi hznfhjrruazow albgzwdhytdqq \r\nzqtrwwrhxtend vdmulrwscqhau qmgveexsmxljo pznqhvyssmriv uxbymubhllxgg \r\npgymtffwmzodc pznqhvyssmriv cxbtstcuiqvqo xqnxcbvccjeeo \r\nqeyjqryrpjydh xwcjtptzjmvcl \r\nzbegdwrgnswqj zapaymhftdwkc qfpcuypankzbv debxlysqpelsl \r\nwguoztswjobgy qeyjqryrpjydh \r\ntiafbvyamsrlx tcgdnnvqgkfgh gqlvyuyutqlto albgzwdhytdqq \r\najfbmitlingmm umheuosxrdkdt pznqhvyssmriv azmquwwimmezt wguoztswjobgy \r\nlgxcwllcjmsmz albgzwdhytdqq nyhfqbpvuwmwj \r\nmlbeuthxurwlg asbockbpozgch lhelcmixssdfi \r\nnbydwtaqkojua kwxxnzbauaqqp \r\ncrnjyyqytmqwa inwuuzfdizily benbwvwklbewe \r\ngqlvyuyutqlto rzofjdouzegmo xqnxcbvccjeeo \r\nqcvatiubegura thgqrjqshfgoj xwcjtptzjmvcl xuxnukzvmtfkv \r\ntxfvscvgmhppl gppqlxapdekkr \r\nkwxxnzbauaqqp ebyrzvogiuguw \r\nulgomyurpfpqe ecopphcjubpxu crnjyyqytmqwa thgqrjqshfgoj ebyrzvogiuguw \r\nmlfjeeclhjghq ogtizuwzpfapd uxbymubhllxgg nizrljlengcvs ybadtcarvceaa \r\npkjjuhofzkmcg ubghztqgvtenc eiyxcaopyzllp \r\ntflrnitabzfnz cbilbbdzadhaa zbooocfpqdckx orvovgypyhuyu \r\nvhagonhgehbph ogtizuwzpfapd \r\nuwvcsxqdboyuh lgxcwllcjmsmz cxbtstcuiqvqo pgymtffwmzodc \r\niixkovsruazed qeyjqryrpjydh \r\numheuosxrdkdt inwuuzfdizily aasfivpcwyipd mlfjeeclhjghq inwuuzfdizily \r\nztnuechsheqku yjnfphzsowtht pgymtffwmzodc \r\nnyifpkxldrurc gppqlxapdekkr vtazguibbzhvb ogtizuwzpfapd uxbymubhllxgg \r\nedbexrfgreelz sauwuiraoffhw entehhsqmaxpb bgpckbjshokeg uwvcsxqdboyuh \r\nxuxnukzvmtfkv uwvcsxqdboyuh cbilbbdzadhaa nvnaiyfujtdeu \r\nvdmulrwscqhau tcgdnnvqgkfgh mlfjeeclhjghq \r\nimjnevtmmbkgj mlfjeeclhjghq btxbiiajcsjlp ajfbmitlingmm \r\nfwsugvvnmizit goqotulirejot uwvcsxqdboyuh ecopphcjubpxu hznfhjrruazow \r\necopphcjubpxu edbexrfgreelz mlbeuthxurwlg \r\ncbilbbdzadhaa lgxcwllcjmsmz ogtizuwzpfapd tflrnitabzfnz nvnaiyfujtdeu \r\nnyhfqbpvuwmwj nyifpkxldrurc goqotulirejot cbilbbdzadhaa benbwvwklbewe \r\nbenbwvwklbewe nbydwtaqkojua rzofjdouzegmo mlfjeeclhjghq \r\nnvnaiyfujtdeu mlbeuthxurwlg xwcjtptzjmvcl oactcxkkhzknr \r\nvlulvfhpmgptc thgqrjqshfgoj goqotulirejot iixkovsruazed \r\ndebxlysqpelsl vlulvfhpmgptc rzofjdouzegmo zapaymhftdwkc \r\nybadtcarvceaa jukvzvgnmtuml crnjyyqytmqwa \r\nogtizuwzpfapd vdmulrwscqhau pgymtffwmzodc \r\ngncrfpuzojnww ecopphcjubpxu xqnxcbvccjeeo \r\nocdgcshulfxln edbexrfgreelz vtazguibbzhvb vlulvfhpmgptc \r\nebyrzvogiuguw vdmulrwscqhau \r\neiyxcaopyzllp jukvzvgnmtuml wguoztswjobgy bgpckbjshokeg \r\nxqnxcbvccjeeo nyifpkxldrurc \r\nazmquwwimmezt pgymtffwmzodc pchvcgicjqibi \r\nzapaymhftdwkc debxlysqpelsl \r\ninwuuzfdizily uxbymubhllxgg cbilbbdzadhaa vlulvfhpmgptc \r\nrdxlergeasqdq btxbiiajcsjlp nyifpkxldrurc \r\ngppqlxapdekkr nizrljlengcvs qmgveexsmxljo tflrnitabzfnz \r\nxcpgrqjklazbj cbjomnzyhzvgc \r\npzltzvinsbvsf vdmulrwscqhau prxdpnwyaamtb vhagonhgehbph prmawlqznszgn \r\ntcgdnnvqgkfgh prmawlqznszgn \r\nbgpckbjshokeg ubghztqgvtenc thgqrjqshfgoj \r\nacdvznnlaynbz nyhfqbpvuwmwj \r\noewnxntfmkuto cbjomnzyhzvgc nizrljlengcvs \r\npfeziugdkrpaj qmgveexsmxljo qmgveexsmxljo xqnxcbvccjeeo \r\ngoqotulirejot albgzwdhytdqq ubghztqgvtenc btxbiiajcsjlp \r\nprmawlqznszgn mlbeuthxurwlg \r\njukvzvgnmtuml thgqrjqshfgoj pznqhvyssmriv \r\nkvdwmyjceqjen vhagonhgehbph \r\npgsmruldpissz lhelcmixssdfi rdxlergeasqdq mlbeuthxurwlg \r\nzmhxajwjeoavn qqxizcqsqcadw umheuosxrdkdt ubghztqgvtenc imjnevtmmbkgj \r\nkdbuetpgiadrj lhelcmixssdfi txfvscvgmhppl \r\npznqhvyssmriv kdbuetpgiadrj inwuuzfdizily kleqqltrhhmib \r\nentehhsqmaxpb iixkovsruazed \r\nrzofjdouzegmo zbooocfpqdckx \r\nlteqzvhspkijh pzltzvinsbvsf kwxxnzbauaqqp pchvcgicjqibi acdvznnlaynbz \r\norvovgypyhuyu rdxlergeasqdq qqxizcqsqcadw ubghztqgvtenc vhagonhgehbph \r\nprxdpnwyaamtb aasfivpcwyipd \r\nasbockbpozgch goqotulirejot edbexrfgreelz cqdlmpkzqopcg \r\nzbooocfpqdckx debxlysqpelsl \r\naasfivpcwyipd uwvcsxqdboyuh ubghztqgvtenc qcvatiubegura kleqqltrhhmib \r\nuxbymubhllxgg iqapxifxgtxgc \r\nyjnfphzsowtht aosaqoktravpb vtazguibbzhvb xhfnttjdlamxb \r\naosaqoktravpb tcgdnnvqgkfgh zqtrwwrhxtend pznqhvyssmriv \r\ntstujakgxsimh debxlysqpelsl ogtizuwzpfapd xuxnukzvmtfkv \r\ncqdlmpkzqopcg benbwvwklbewe mlbeuthxurwlg lteqzvhspkijh orvovgypyhuyu \r\ncbjomnzyhzvgc lteqzvhspkijh uwvcsxqdboyuh cqdlmpkzqopcg \r\ndzazbgmmpfvlg gppqlxapdekkr qeyjqryrpjydh rdxlergeasqdq hznfhjrruazow \r\nqfpcuypankzbv nyifpkxldrurc tiafbvyamsrlx \r\nkleqqltrhhmib ulgomyurpfpqe oewnxntfmkuto \r\ntasifzrfubsgc nbydwtaqkojua kdbuetpgiadrj \r\nnizrljlengcvs imjnevtmmbkgj ajfbmitlingmm pznqhvyssmriv nbydwtaqkojua \r\nzjeudvttofebs qeyjqryrpjydh qeyjqryrpjydh ebyrzvogiuguw \r\nqqxizcqsqcadw eiyxcaopyzllp zmhxajwjeoavn pznqhvyssmriv \r\nxhfnttjdlamxb qcvatiubegura \r\nhznfhjrruazow zbooocfpqdckx aasfivpcwyipd \r\nlhelcmixssdfi oewnxntfmkuto oactcxkkhzknr \r\nsauwuiraoffhw gppqlxapdekkr lteqzvhspkijh azmquwwimmezt kleqqltrhhmib \r\nxwcjtptzjmvcl pchvcgicjqibi oewnxntfmkuto \r\nvtazguibbzhvb vdmulrwscqhau prxdpnwyaamtb \r\nthgqrjqshfgoj gqlvyuyutqlto \r\niqapxifxgtxgc asbockbpozgch \r\nalbgzwdhytdqq bgpckbjshokeg xcpgrqjklazbj qmgveexsmxljo nbydwtaqkojua \r\n0\r\n";
		String data4 = "8\r\na b m \r\nb a\r\nf e\r\ne f\r\ng a f\r\nd e\r\nc f\r\nm f\r\n0";
		Scanner s = new Scanner(data4);
		while(s.hasNext()){
			int n = s.nextInt();
			if(n == 0) break;
			Map<String, List<String>> graph = new HashMap<>();
			Map<String, List<String>> definition = new HashMap<>();
			s.nextLine();
			for(int i = 0; i < n; i++){
				String[] words = s.nextLine().split("\\s+");
				for(String word : words){
					if(!graph.containsKey(word)){
						graph.put(word, new ArrayList<String>());
						definition.put(word, new ArrayList<String>());
					}
				}
				for(int k = 1; k < words.length; k++){
					graph.get(words[k]).add(words[0]);
					definition.get(words[0]).add(words[k]);
				}
			}
			List<List<String>> sccs = findSCCs(graph);
			Set<String> subdict = new TreeSet<String>();
			Set<String> visited = new HashSet<String>();
			for(List<String> scc : sccs){
				if(scc.size() > 1){
					buildSubDict(scc.get(0), definition, subdict, visited);
				}
			}
			System.out.println(subdict.size());
			System.out.println(toString(subdict));
		}
	}

	static void buildSubDict(String word, Map<String, List<String>> definition,
		Set<String> subdict, Set<String> visited){
		if(!visited.contains(word)){
			subdict.add(word);
			visited.add(word);
			for(String defWord : definition.get(word)){
				buildSubDict(defWord, definition, subdict, visited);
			}	
		}
	}

	static String toString(Set<String> set){
		StringBuilder sb = new StringBuilder();
		for(String word : set){
			sb.append(word);
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	static List<List<String>> findSCCs(Map<String, List<String>> graph){
		List<List<String>> sccs = new ArrayList<>();
		Map<String, Integer> dfsNum = new HashMap<>();
		Map<String, Integer> dfsLow = new HashMap<>();
		Set<String> onStack = new HashSet<>();
		Stack<String> stack = new Stack<>();
		for(String word : graph.keySet()){
			dfsNum.put(word, UNVISITED);
			dfsLow.put(word, 0);
		}
		dfsNumCounter = 0;
		for(String word : graph.keySet()){
			if(dfsNum.get(word) == UNVISITED){
				tarjanSCC(graph, word, dfsNum, dfsLow, onStack, stack, sccs);
			}
		}
		return sccs;
	}

	static int UNVISITED = -1;
	static int dfsNumCounter = 0;
	static void tarjanSCC(
		Map<String, List<String>> graph, 
		String u, 
		Map<String, Integer> dfsNum, 
		Map<String, Integer> dfsLow, 
		Set<String> onStack,
		Stack<String> stack,
		List<List<String>> sccs){
		dfsNum.put(u, dfsNumCounter++);
		dfsLow.put(u, dfsNum.get(u));
		stack.push(u);
		onStack.add(u);

		for(String v : graph.get(u)){
			if(dfsNum.get(v) == UNVISITED){
				tarjanSCC(graph, v, dfsNum, dfsLow, onStack, stack, sccs);
			}
			if(onStack.contains(v)){
				dfsLow.put(u, min(dfsLow.get(u), dfsLow.get(v)));
			}
		}

		if(dfsLow.get(u) == dfsNum.get(u)){
			List<String> scc = new ArrayList<>();
			while(true){
				String v = stack.pop();
				onStack.remove(v);
				scc.add(v);
				if(u == v) {
					sccs.add(scc);
					break;
				}
			}
		}
	}
}