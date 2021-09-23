package com.sizzle.sort;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by test on 2018/1/22.
 * 插入排序算法
 */
public class InsertionSort {

    /**
     * 1.从第一个元素开始，该元素可以认为已经被排序
     * 2.取出下一个元素，依次与前面已经排序后的元素进行比较
     * 3.根据需求(降序或升序)和比较结果调整元素的位置
     * 4.重复步骤2-3
     * 平均时间复杂度(执行一个算法所花费的时间)：O(n^2)
     * 最好情况：O(n)
     * 最坏情况：O(n^2)
     * 空间复杂度：O(1)
     * 与冒泡排序没有本质区分，平均时间复杂度都是O(n^2), 但是数量小的情况一般都使用插入排序，这是因为时间幅度是很宽泛的概念，落实到具体的场景，因为插入排序过程中赋值操作少于冒泡排序，
     * 一样的数组情况下，插入排序的花费的时间要更少
     * @param numArray 需要排序的数组
     * @return  排序后的数组
     */

    public int[] insertSort(int[] numArray) {
        //未排序集合
        for (int i = 1; i < numArray.length; i++) {
            int pengInsertNum = numArray[i];
            //有序集合的最后一个元素
            int j = i - 1;
            //已排序集合
            for (; j >= 0; j--) {
                int sortNum = numArray[j];
                if (pengInsertNum < sortNum) {
                    //如果待插入的num小于已排好序的元素，则将当前元素至排序区域最后一个元素统一后移，然后将待插入的元素放在j的位置
                    numArray[j + 1] = numArray[j];
                }else {
                    break;
                }
                numArray[j] = pengInsertNum;
            }
        }
        return numArray;
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        //int num[] = new int[]{5864,-12333,4151,-6239,-10306,10866,-7013,13195,-8855,1150,-560,3227,10387,-2329,5169,-19527,2689,597,4255,-13697,12495,19719,15995,8991,-12859,5601,8195,3943,16216,-19677,15590,10316,-4255,45,-6508,-5416,4993,15278,-6015,-18843,-8400,-6994,3608,-7695,-14698,-2684,8753,18019,3266,-10860,-14354,8708,19037,-16188,4932,1403,-10571,18368,-9786,13410,1686,-17352,-13827,6647,16747,2664,-15830,13673,-10248,2115,-19074,-919,4382,18718,-7449,-16031,333,-14066,-2505,-9975,-226,-18986,17487,-3498,-17203,-3506,8462,-191,10563,10160,12627,-8306,9439,-16640,4586,-12067,-19904,-5607,-15989,15651,-18358,-850,-850,3472,8969,13113,1269,4808,123,16848,9857,17099,14566,4854,-19826,957,-10325,-8686,11542,-10343,-16353,-9446,-18914,3242,5897,-19881,-16532,14827,-5840,2873,-10823,-4545,5028,-4985,15482,-8196,14376,-11370,4044,-18929,15051,5562,15969,-4135,13438,12928,-5472,10521,11392,-16511,461,12535,12429,13353,5861,6523,2158,16888,8264,580,-18042,-10113,-809,3072,14636,-12239,-18102,8235,14993,-15364,-19620,-13386,-18777,-7497,5057,-8141,-3481,10271,-9995,-7095,3227,596,-16996,-18539,1110,-13797,10370,-17772,11653,15077,18369,11289,-19190,-8459,9841,-7993,-5988,-12463,-11450,503,17321,11286,-2143,-10043,2124,4675,9222,-7730,-7148,5883,-16544,-10609,-14124,2870,-17110,-8522,-15060,-3181,-7346,-14611,-19707,10749,-18521,3296,12425,-12432,3861,5762,19198,-8727,14496,38,6655,9821,16008,18685,-19707,-10857,-18835,4629,9009,8461,16103,2364,-14902,17870,-18850,14524,17172,-6205,11312,-10303,1327,12037,13534,14116,13747,17273,-113,8142,-19673,6987,-2515,6126,19642,12692,-979,-12819,-12377,-15499,16078,9489,-3969,16604,-13093,-4785,-5054,-5257,1534,13772,15112,-19759,-751,17675,-9738,555,5138,14450,14272,16880,-5220,-7764,11064,-10502,-2882,-5117,-9418,-12036,2598,12617,19374,13121,-10192,-18760,4294,15642,9654,-17869,-14160,3552,-9452,-13142,-2349,-208,12323,-17454,-17072,1480,-161,-12457,10350,-17322,-1822,12826,14955,-662,-3686,-6296,-19302,642,-6904,4604,15696,-3320,724,2576,18191,-19446,15831,17370,-7224,3815,4365,-10963,7647,7758,1529,8356,14607,19512,-10514,-1058,-17052,10965,17726,17005,-4978,-15012,-4975,1795,6553,-18402,-7335,-7793,-7712,10322,-8126,-2696,4688,-14516,-10898,-17626,-2460,18421,-2333,775,6835,6725,-18022,-17639,10787,-3182,-631,-14757,19682,-15708,-4664,-9373,5250,-6130,-3924,10641,8678,-7344,9805,-13690,16269,9536,17301,8173,-18279,2823,-12339,7311,-1310,5238,-7580,-4063,-8516,-15774,16684,-335,-6790,14049,-11259,17978,-17973,-19988,-624,5760,-16684,3449,19295,-3579,16517,-6761,-687,-3604,-8972,15385,18528,15345,-10113,-10556,-19856,3246,-9107,9194,13158,-13342,-10022,-6172,-1886,-10750,10128,4049,10927,10150,2291,17561,17778,9275,-9819,8640,5120,-5987,-16314,4481,672,-14913,-2800,4579,-3881,15933,6926,-9799,-3724,-11567,-18522,3216,12599,18224,3613,-1742,-19168,15440,16516,-9768,-18317,-5029,-5797,5455,-19063,6491,19997,11537,9757,4059,-692,-5970,5394,17099,-12209,11500,-19091,-979,-5456,9835,11561,7979,4956,8526,14164,-19348,-13343,-10622,-157,12296,-13832,8993,14066,13162,1630,16311,-6450,10344,-2866,-1402,-13282,10018,9575,-662,11270,-13106,5231,-14945,2230,-7583,781,3311,1663,13666,18233,-14758,1709,-5867,18995,17437,18598,19346,-6041,4713,-11364,9385,-546,-11058,4861,-8006,-12091,-7326,-6832,11336,2918,10788,7996,5232,14929,16012,-15576,246,-11278,204,19114,6160,19231,6670,19566,-9508,7081,-693,-11289,1989,5336,9998,-3254,-18144,2992,-17955,-10725,-13461,-8031,83,-14223,14530,-17722,-17475,19865,13393,-6431,-11082,12314,9913,-16465,12757,14520,-15337,6595,-823,-19911,9132,4520,1768,-11372,11280,19485,-16083,9642,-8557,-9113,12333,-10261,-13605,-2376,-6951,5674,-7408,-5302,7734,15948,18347,9299,16003,15553,13817,17957,16281,-3286,-8009,18760,6017,9967,3377,7225,-18857,-10561,5121,14393,16438,-13016,1809,-13989,3472,-115,-15790,731,-4430,-4529,12854,15321,265,5651,-14538,-4574,5152,17077,13802,12885,19611,-12907,13990,-16630,-783,5491,11293,14528,422,7083,-14678,18128,6763,-9321,-10619,14641,-11938,19994,2454,2578,3716,-19567,-14754,-3896,-2005,-16013,-17468,116,12428,14945,11669,-8409,-12776,19344,-5776,-4843,109,-5419,-13538,8014,-13718,-2900,18709,-14625,-1597,-12073,15173,10211,18839,10096,18752,16977,6697,11458,3885,-8311,-2598,6813,-618,16414,5705,3506,18570,16236,6166,-12985,-3858,-6486,-19593,-18366,12985,-18585,5109,-201,6851,-16232,18356,9222,16649,-55,3062,19337,1419,-11959,1166,8805,17591,-16281,-17876,7609,14138,7824,13596,-19974,-5136,-2073,9055,11068,-3093,13173,-12282,11054,2001,-12,-9853,-13131,-19692,-17466,-18627,-9965,-1141,-14571,-10181,1802,-13295,-18598,-18562,18627,-5599,-8625,-1706,6632,9081,8629,-4365,10700,-14575,12340,-14716,-16938,-3376,-16584,-19610,15154,-18068,-1151,3166,4125,-10066,9753,-13292,9561,-17610,10960,8813,-9970,16795,18089,-16924,16339,-7495,-17155,3683,7599,15803,2208,-18634,134,5890,18732,18286,11159,-301,10188,17225,-11813,-19262,16082,14777,16048,896,-17801,-1680,-1612,-15375,-3816,14971,-3796,12901,18218,-7566,-8509,13642,7314,19714,7796,-1738,-14987,8554,-14385,-17294,12465,-9641,-12341,-2386,-8943,-17624,15335,1361,16085,-4491,-6910,-2186,6523,-2486,-4192,19904,-6132,16067,-7896,13069,2004,17908,3822,-16291,-4173,-6789,-19579,-9176,-12190,-6214,-4375,7907,-14193,-10417,-17329,-11492,13737,12356,-391,13978,-7937,16209,5417,-19169,-7612,-17209,-65,-12388,-4993,-14371,-11660,-7898,-425,-2065,-13731,-7789,-12395,-12092,-5453,-19083,-10414,6947,13082,12092,6022,-7110,-13705,-7014,11603,17419,-12308,-201,-8824,-19256,7031,15409,18732,-17308,-5738,1417,-5505,6103,-7348,7973,8866,19168,7659,12644,-17851,-18321,-17666,-8153,9862,-2695,-12478,-13,980,-1973,-13275,-9509,-16422,9151,-3459,3974,11826,-18289,6433,-18907,9744,-13782,19628,-3588,17073,-6218,-16716,13216,14283,-1836,-3606,-10552,0,-4115,19645,-9583,17133,10908,-1568,-13747,-1749,-8040,-6679,5883,3653,14328,10338,-8470,12104,-10723,10129,-2670,291,11555,8520,-10482,10923,-1522,-14409,-11346,15894,-14114,2348,2554,11416,10054,10795,-8453,-13287,14435,-4460,-7382,-18927,-1885,18530,18449,1666,-12439,3184,12087,14785,-7917,-9501,-17415,-11223,-9661,7290,-8351,15175,2119,10687,13548,4893,7152,368,4293,-3381,5619,-15812,-5501,-12209,13989,-13222,19438,5282,9841,1575,18769,7853,427,-11957,-8345,16559,3690,-15707,14815,15833,-295,-18575,7236,-5343,-6944,19622,6467,2983,2925,15571,-16749,17261,17973,-18105,-6296,1896,-8582,9135,-2713,-9445,620,1402,16006,-10211,-16277,-949,-12838,-8368,7737,10499,15784,8485,-12449,5969,-176,3259,-4180,-238,-5859,-12868,-9195,18931,-10023,376,16406,12657,4770,13883,13776,-6287,-12345,-1258,-14440,-12180,-17633,-14702,-15379,12968,-17671,12436,18647,14124,4617,19363,7705,4503,11898,-1173,-12957,6081,13246,-8649,-19818,-14256,15361,13312,-13253,-16650,-16610,-8763,7640,-7636,-12622,-1334,697,5057,-3932,-10477,-10318,18549,9717,6489,9514,-14286,6149,759,-3167,208,1001,-11390,-13118,18010,-19842,-11272,1607,8713,15096,18367,2675,-637,-9612,7547,-305,9244,-17451,8092,-8419,-6094,3990,-12151,-13990,12994,1157,6056,3992,-1729,-2233,3568,-18042,-11739,2346,1403,6621,17178,-2587,773,-2158,-19104,-10627,12435,-11237,-11440,5253,-1108,-9649,-17775,6049,980,19531,-9647,-10803,9398,-18704,-2955,2440,-3485,596,-13946,9253,-6303,-6449,-14035,15166,13342,-9582,-14029,14981,4531,13114,1204,-4811,8681,8465,-14441,439,-368,8482,17334,-1418,-14300,-2298,2513,-9218,-4076,4719,-9734,-9903,-9439,15144,-2921,16130,-19774,19039,-10221,-1518,5488,-6930,-3180,2070,-7604,3369,-5011,8496,17910,5611,5601,-1581,-2085,18789,5858,10939,15233,-4430,-19950,-11754,-9383,-293,17993,-8371,-17686,-14001,13237,9266,-6769,4865,1478,15307,-5562,-19480,-9142,3331,-12518,7469,16275,-4071,-18591,-6090,11825,18974,-4928,-14421,-12977,-1589,-3449,4202,-6052,2544,269,-7997,12240,-9296,5606,-11050,14079,-14157,-15551,4462,3289,13826,-5552,16631,-15974,17439,8157,2479,15257,-10454,-18060,-18147,-16107,6492,11565,-19676,-7,-17396,8783,-13865,-8661,12008,-2479,4033,18490,-1813,-17482,-5766,5880,703,-16908,8630,-10457,17664,-10136,-18508,17374,-14562,-12704,2678,-5727,9415,-18136,-11290,-15213,-9846,15572,-8112,-7374,-7170,6289,-7225,-18109,-13230,-17650,-5453,846,280,17101,-2414,10436,-18172,-5308,-2330,-16916,-12891,16298,-1350,-13275,11049,3387,-11660,-16790,-13266,-13008,-19986,-9110,-18243,10741,15401,1521,12905,-16712,-1297,-8167,6679,4719,5607,-6586,-7753,-257,17981,7982,6763,-19900,-5869,-4216,-1644,12549,13887,-14405,12603,5837,18915,17140,-18783,9852,14439,-5758,-14161,12615,-1766,-17036,7255,-4644,4527,-8482,18833,8402,-12746,-17241,-13869,-17333,-6897,-8648,-15237,2192,-13643,4533,-18086,-12375,18366,7221,-18173,-10993,12220,-13753,-12122,-14844,-17395,-17469,-16796,-9649,5458,14023,10749,-374,941,4275,16462,9528,19479,22,-6408,-3855,7664,19043,1098,17306,-743,16725,12557,13465,4792,-15149,-14285,2931,-17105,-15381,13281,-9678,11052,-11870,15257,-14572,-17837,-11294,11199,15444,-18683,-17696,-5327,-4107,5290,9432,16911,18002,5237,6981,10653,15373,19525,8030,6781,-14153,-16519,2105,11408,10856,14546,-70,3725,-9159,-14760,-8039,18651,14337,18659,4251,-15612,12165,-820,5267,10452,-1517,-10810,-5900,-14095,11957,-4160,-5745,-6707,-3029,-14327,9566,-2432,-5072,-12807,-7105,5059,-911,-14844,5638,-17964,19015,2556,-9181,10746,-10650,-13144,-13777,-13567,7494,7103,14221,2015,17138,8119,-4017,13499,10867,5598,-12301,5908,-13549,-6565,9198,2605,1690,-14045,-12508,7576,5413,-19597,14294,-3076,17821,15487,16512,-4392,2375,18287,-7859,-18535,-3734,-9115,17118,4270,-12233,-17350,-9635,-16219,188,7178,-17975,-6979,17522,-19962,-2352,-7302,6428,-8161,-9324,12637,16800,695,617,4491,8199,-19275,10367,-4242,-1111,4570,-9886,5991,-6713,-7406,-3348,16049,-10744,2525,6220,15979,2046,-3177,18935,9988,17845,14233,-13393,12679,14439,9965,-4938,8899,12870,-6384,9197,4398,11428,-13865,-2992,14138,-18240,-12398,-5191,12793,-2979,4774,-1431,-14120,-2554,2737,6491,-13973,-17160,322,831,-6790,12189,-11180,-3198,-2896,-2143,-6473,17381,2512,-2204,-15206,-7050,12421,-2755,15076,-3901,8476,8219,-1353,-3376,2536,19660,-12848,15172,9157,-18106,17635,10340,-13453,14346,-17024,-10186,15599,-3842,9122,2012,-4795,12662,1145,-8941,1107,-3971,16832,14642,-10528,3202,4414,18442,8379,-6867,4969,17272,-14122,714,180,17719,-12878,-3181,-2390,-1577,-16798,-16634,8070,16343,-11527,15595,-1804,9773,-17925,-8415,-3374,-10737,11107,3500,19602,-8468,-15270,1687,-14610,5759,6721,-16257,-9656,18970,14570,-15638,-14087,-12540,10142,-12112,-16807,-4614,4056,13773,-12082,18054,-9891,-1707,-5179,-11157,-1741,-3203,2787,2199,3190,2494,-13706,1747,4526,-17786,1194,10065,6953,3701,19812,-3113,-10722,-8403,18839,14560,-3393,19200,-13127,-9408,14370,-10674,16474,1268,4870,-7776,16368,-6301,-14507,5993,11816,84,-10388,14765,12179,-18528,-16505,7470,-1046,-10054,-5629,-7555,9230,14860,14216,16072,19715,10980,16366,-16767,-5172,-17869,-18456,7813,-4841,13335,6949,11963,18617,-2417,-2246,-16971,17727,-14890,530,-8236,17952,5363,-1324,5405,-10514,6547,-4340,4803,16212,10785,1939,-16675,-707,-1249,-9956,-7671,-18827,-12240,2569,-17648,-4264,12046,-90,-6970,-8271,-3715,-16266,-10584,8754,6540,-6084,151,17779,4434,-2481,8528,-16606,-1346,-5440,18120,8220,18813,-13188,-2183,8383,7976,12486,17136,-6878,-775,-2100,1489,2640,-8719,-14477,12482,9861,-12663,-5633,-2725,13794,-16663,-17166,16830,-10293,11414,-10299,5141,764,19380,13498,442,-2835,-8321,2630,10348,-3116,3178,-1369,14465,19854,-5448,-7129,-16143,-12699,17279,8760,-15713,-1134,7662,9286,-13871,9708,5872,16791,-4907,7453,-1278,11424,18952,2083,3592,9787,-11594,-9390,19541,-7796,-5065,97,-7107,-8637,14759,-6973,3945,15801,9577,-14393,-10241,6539,2782,6705,1904,17295,13921,-12594,11490,-12704,-4590,3467,6810,10884,-16263,-7244,-14537,18703,-6307,6972,17697,-2879,9772,-1542,5813,-4621,15754,13984,-14298,-6255,5912,17033,17239,-14270,-7546,17317,12727,18957,-14804,-17363,3225,-10744,-14384,13622,10849,13811,9680,-917,-1795,-2822,-13725,-18785,15972,-16243,11989,8122,-4655,9216,524,-15816,2662,5722,19901,18286,-6083,8687,-9209,-4077,15077,16972,16325,-19894,-16801,10304,-18831,-16496,-15176,-12729,4147,-14999,6914,-16739,-17236,-18863,-4968,10928,-18149,1682,8229,10902,-19801,500,-19643,17620,-15892,-18586,17123,-14685,-16487,8457,-16994,-3284,6534,5732,-17920,-13005,-9041,7370,-11068,-3004,15532,-3454,-5983,12948,15817,-8467,-910,-10909,-3770,-12013,14833,16146,-942,12280,-4461,-15530,10138,-9902,-14008,-16527,13441,-5242,-5840,-13669,6480,-1730,-5854,-6870,2523,16848,18623,7179,19046,12351,-2345,-15493,-10966,-11508,16035,12015,4753,19460,3238,19662,-17375,-6992,-11206,-14026,18398,7476,-3549,-5201,-1676,9887,1818,-8839,-3936,-17061,5786,10822,18291,-18882,7668,-5525,-11655,17321,5845,6508,-17367,-8825,716,4817,5742,-17122,-18566,2531,9910,18579,-12985,12186,-7072,-5872,16427,-11065,19752,13800,11474,-16293,-19377,18433,-15050,19003,-17380,3350,4950,-19245,-6169,9012,2547,-8766,-15596,1672,19257,-19823,-19362,11627,-6008,7157,-9431,-16008,-8305,5131,-4160,-6781,17846,15202,-13643,17288,6900,1488,-15354,-4970,11550,4599,823,-9234,-19374,15544,-16093,-8970,-11765,7901,10533,-5176,-10212,-13035,-9224,7667,-18533,-18712,13380,-8370,-9552,18958,-15762,16963,19566,-17250,-9760,-2376,1133,-3973,-18271,4941,-6875,16293,2147,-16658,-14100,-2921,14345,-6633,8066,-6939,18749,-4319,15403,-13309,2224,-18414,-11127,-8892,17427,7040,-12400,-19366,18490,4040,5943,-2373,16044,-469,-6617,-1855,9512,-10509,-8490,-9538,-16577,-16791,-14217,2329,-19240,-8093,7070,-6889,17319,-18670,10561,976,11128,-453,6064,19953,2670,-11648,14138,15764,10262,19420,14407,12829,-10200,1600,6775,10575,-11727,-3803,-777,-2864,-3681,-12832,16503,15123,-17788,9109,14945,-8224,-5120,-8872,12149,-12778,16227,-8094,-15442,-12892,16256,6413,5324,-16085,10069,-354,-17447,-114,-8332,4155,13813,4909,-2675,-16236,-3541,5689,-19317,-7734,-19805,9153,416,-17170,-6784,-13638,17379,-284,13299,10903,-1821,16506,-14815,16269,-16873,16375,5364,-4190,6041,-6280,11535,-17539,-12565,-18563,8567,-15179,-268,-17458,16007,-4300,5820,-15371,-12664,-11694,-12968,160,-294,-16407,-16184,-560,-6067,-13868,3293,6264,-19274,9785,12987,286,-9365,11978,10658,18979,-4066,19688,-8021,7841,10214,-2629,-9983,-19863,10718,1399,-9382,-15278,-9313,-13999,9346,-13089,-1526,4351,19053,2130,14496,-17998,5217,-13937,-18286,-3019,17165,15,-15155,-11062,-13971,-15416,-11082,-11860,4000,6178,5440,-19673,-3360,19833,-1062,-10487,13883,8295,-14045,-9794,2444,18094,2671,16008,-19941,-2461,-12360,2675,-12336,-17792,-6067,-19676,9658,-19012,2319,-17156,1849,3843,3732,11628,-19110,-885,10346,7469,-13311,-16536,-12897,-14897,-8940,-14230,10059,12250,1453,-13108,-1153,12463,17630,19391,18941,-760,18942,1563,13740,-2976,12543,8819,-13712,8008,5177,16034,13908,-14927,16147,2892,-9439,2316,-8161,-5264,-12416,967,-11968,17219,-8593,17879,-8572,-4922,19563,-17706,15438,19435,5573,-1840,-17545,3421,2695,16257,-16682,-12841,-15153,15609,-12209,-8491,-1719,-15265,-7838,4594,-83,520,8508,-13141,13537,17528,-8732,-13520,9640,-7335,-18699,274,8044,-10152,16990,-5059,-6197,-12925,-10227,-8965,-18055,80,19420,-6027,10623,16155,2078,7185,-11449,17465,-12712,9791,10287,-5326,-6408,-15954,-1234,-14519,-12305,-18499,-1003,-11529,-4525,3738,-18492,17030,10884,-17970,-18371,-2790,-4857,19647,7054,17663,14897,-10535,-7244,16819,4476,4793,-8259,-17533,13091,12312,9306,19571,-7585,-12412,-12989,3649,-10223,6256,6019,-2685,-2723,-13453,7306,467,811,16712,18381,15055,11344,8097,-4352,6444,5295,172,-16700,-2552,17555,-1096,13302,17371,19220,-1772,-1003,7129,482,14155,-3961,-11405,9199,2243,-18386,-19938,-16742,-1222,-3927,-15458,-7092,-14435,3391,6933,-10350,15032,-1149,8816,-17379,-12285,10327,-1127,-6344,9118,-4344,-1039,5397,-12726,-7388,-19020,13339,-18033,6654,16988,-2367,11038,-5022,12074,-19623,1903,1635,1145,17708,-14269,17723,10342,-14419,18885,1054,11118,-1551,-10719,-19842,-9946,6306,-9513,5987,983,-14659,15116,-1255,19885,3917,19960,1053,10226,-19685,-11339,-16551,-5789,-12144,-11874,3092,8005,656,-10706,-8698,-19803,-2553,65,18108,-2323,4064,18203,-7551,-13946,-14831,5688,-15494,15383,-5208,7159,-5157,15846,-19536,-18652,17806,15862,-6349,7450,16537,19642,12087,9511,6908,15309,-610,7796,10576,-4359,18712,-14883,-10370,17263,7790,14704,19778,8362,-14739,18326,841,-3440,-5378,17312,8779,509,19297,8498,-13697,-1975,9435,-14150,3896,-13667,-9767,-16993,-6731,-18572,-5210,-17617,4044,-11547,-4548,7886,1072,15255,3272,-16211,-7087,12520,10004,-10765,-5460,-14442,4899,15443,12926,13174,-3683,-10715,16781,19759,3903,-10116,-2486,-19,19313,-2667,11932,-14182,3978,15057,-18385,11715,-15715,-3665,1620,-2956,-5631,-12464,13920,11651,-9619,-13780,4126,-12041,-10057,-19195,-18955,-13110,4906,4729,9008,-19784,6352,18901,11895,-4688,-15952,-542,-9597,889,2762,13401,-15169,-6224,2057,7063,9731,-7816,-13772,2971,6603,392,17253,6746,-16218,18295,361,-14631,10508,-18432,-1758,-17297,17374,-12667,-6933,8898,-16072,4176,11487,-2114,13381,-7171,-342,6014,10328,-15993,-15188,3741,-5995,-1610,18040,-12321,5495,-2262,-13150,13440,-19323,-19284,7611,647,-15955,4745,-9654,-4585,16891,15223,6997,16203,-12280,-201,-15630,-6590,-9778,-7928,14073,15216,17396,4385,18174,11924,8689,-13217,6172,7544,374,-10507,13277,-3567,-15249,-13705,9749,-15446,10401,9329,6346,-13985,5059,14137,8893,-12611,14527,11121,-18933,12693,-5322,-6582,8923,-7636,17647,3249,-11551,-19812,-892,-16655,16349,1453,-10777,-14884,-12894,13650,14107,16644,-13210,13558,12939,3016,19140,-8941,-18818,-18388,2140,-10048,4917,13993,16971,16514,-10521,-14368,19857,7416,6273,17922,-9745,-15114,17995,-1282,-16991,-18415,17597,949,16096,9720,4265,-3197,2707,17821,-19294,10559,-7321,8544,991,13291,11110,14345,17070,-14094,-18628,-18042,7016,-13503,-6331,-6550,1826,-18402,4149,10591,15493,18398,-7111,2042,7410,17786,-19344,-16802,-6829,-13637,-12313,-8648,-2838,6715,18678,-6668,-2846,5286,-12513,-4793,-4371,-16173,17795,-2120,-19490,11850,11886,-19757,2026,3623,-15481,18765,-10478,-2590,12926,3601,-5487,15098,966,-6336,16597,-3446,16502,-19359,618,5120,-6858,-8970,-3409,12108,19183,-5330,-11803,-6268,-5552,-14166,3407,-7553,19074,-4447,-2071,11509,14833,-7638,15316,-7270,16248,7664,12642,-9676,16885,-13890,-19170,12021,-10380,-16474,7390,-15835,-12294,-6581,2890,8542,-18939,4543,-1820,12337,-7297,11844,-8407,-17345,-19327,1398,18172,9482,-12169,-5041,8976,-1470,15772,18239,-14888,15660,-9851,-17693,-17952,819,-15176,-5029,12007,9211,-15165,-2615,-5793,-12787,-9224,-7411,13122,-10986,-12725,12127,4225,16166,-1878,-13225,-4631,11220,-10400,-1817,-19940,-4292,-13118,8236,1497,4685,7346,13812,8162,9505,14392,-11582,5001,-11183,6823,2978,-15852,11237,-16611,-16952,16435,3982,13081,8234,15062,1719,-16648,-9774,-15565,-2074,-4488,-14930,-18370,19307,-5203,-5771,16060,-9415,-12428,9961,18558,-11163,-18278,-8220,2783,-14184,-9432,17283,7881,16451,-5478,7925,-18298,-13643,-15540,-11973,14330,1111,19456,-2772,-18155,6416,15083,5047,9724,-12212,18954,-8896,-18858,8002,9300,19697,-4465,1462,18865,-11856,-18252,-7282,8023,-11209,18347,-16637,-10448,9281,1355,19205,507,7496,14648,-10566,-187,8262,11641,13277,15649,4257,8497,6482,-13377,-17233,16596,-11287,-1527,-1200,-1052,-13567,10319,-9789,6298,7985,-3821,16880,17907,-14147,15035,19715,2,14840,14783,19879,-6108,-11547,-1124,11222,-8200,17657,-4095,-13021,9753,1384,3068,17735,17294,1299,8632,8881,5739,5029,-378,10028,-6806,838,-17737,-6709,-4927,10801,14900,-2196,16250,-1105,2802,-17394,7768,-6446,-17939,10919,-5445,6465,8068,17545,-7507,2049,-8653,14039,-5174,-14280,-12779,-14506,11799,-9271,2665,17601,16761,-5798,-489,7410,10323,-3301,12558,14786,9856,11607,-10516,-19821,-19449,19990,13423,-18927,17587,-4020,-14148,16714,-19024,2481,-5403,-16381,-14220,1490,2304,17551,-4800,-1488,-18310,10321,-18731,13637,13421,18180,16723,10223,7198,1277,-11910,19762,1201,-18160,5637,-6812,1412,-17513,12363,2963,8232,1457,4013,-10341,12814,-15369,-18704,-6408,18506,-18568,1148,2895,13618,-12714,2306,8177,-1469,-10597,14993,-6341,9666,-3544,14884,14843,19209,-5187,3551,-5561,8504,620,-5700,-18480,-12284,-7911,14510,17769,3361,-18691,-17955,-5438,-16581,-11577,-18428,-15349,-2165,-8602,-18460,280,-14210,18804,-17568,-18171,-15763,-10654,7531,-16006,-10587,6222,18061,2250,5706,-13459,18912,-1192,-16619,-2538,-9971,-10612,15404,-6817,13055,2140,13582,-2881,11573,17309,-8982,-783,-15508,-8614,-528,-14543,-4480,-17733,16789,2363,16873,15447,2480,-15656,1575,17583,-5182,-17436,13982,16704,17104,14079,-233,1563,-12929,7120,-5996,-9574,3944,-15948,-3092,17792,-1018,9247,2806,-18561,-6267,13426,-10640,-3077,-19788,-6324,17612,-14250,14899,-4581,-8258,-15497,-16027,-16227,17512,3230,1065,-14233,-19279,12548,11397,14026,-1381,8344,-15444,-7310,-6903,19815,15135,5267,-2315,-5966,-762,-19103,1751,-8927,-18914,-1424,-3288,-5718,-1271,-7412,12286,-11106,-8983,-5401,16246,4,17276,15223,-19672,-7511,10481,-4590,4532,-3247,2299,7902,13120,14544,16586,6534,19858,14128,-17089,-18577,5024,9146,16025,-7074,9086,5834,14587,-6827,1578,-11800,-18808,8307,15728,-7076,-9779,10542,-10685,12493,13893,16897,-15853,17191,12995,11743,9418,-14579,-13293,14283,-3753,-4263,12162,5869,16792,-1681,16129,8252,-2729,10871,-14245,18524,-15269,-716,-17890,-9183,-1383,-19425,-19422,-16056,3877,18904,18391,10813,-2957,15343,-13923,-2472,-4703,-19225,-16438,-10236,1544,4062,18181,-15379,-13152,-7986,8103,-17467,-5611,-13691,-13007,7614,8743,-18275,5510,-13589,-16112,-8736,-1096,8605,15537,-16967,-13193,4733,15808,-12821,-3774,16996,-3663,-14780,-10221,-5254,-15343,8662,-13698,636,-9086,15627,-19448,17368,11531,-12834,12541,2308,16299,-3314,-4984,-3344,10020,12327,9976,13765,-2921,-6978,6773,-7665,18010,19719,-7165,-2852,-731,15165,-4437,-17031,1587,-3089,16642,-6393,10661,320,2493,4970,-9366,10164,11543,-1767,8225,13353,-14011,7335,-17228,-782,2837,-6765,306,3273,-16367,-11084,-19948,-10930,-1007,12330,13705,15657,-1858,-16350,-957,13477,3651,16769,-7604,15172,-13605,-4263,-10017,5844,12570,13851,-1846,7135,-13290,12914,-15532,-9737,-19085,-16035,13756,-14207,-8786,11286,-12263,-8583,18627,1181,-5569,-2014,-9422,-13361,-13632,-18048,13125,8895,11252,-14652,-19042,4539,726,15832,12279,1021,15875,19709,-19418,-15323,-9932,-959,11771,369,16249,-14949,12160,-16163,-4882,9959,2111,17752,-12034,3875,-16502,-10391,-4725,11696,-8280,-11905,16039,5152,-2389,-6851,3508,11530,-10331,9821,11834,8594,-16877,-16467,-17529,-8236,-16596,-17494,-18255,12797,11247,-6818,11850,2690,-19409,-17087,-8830,-10902,5972,-7784,-12933,9366,12355,-3082,-8441,18382,-22,-10976,13182,-12414,16255,19783,7932,5695,5066,-17234,-7279,4771,14075,8221,-5520,-8359,18096,-7197,14652,155,-7161,13443,-267,-11570,-17230,2432,-13922,-17655,2571,5964,-14320,4373,18770,-9895,7220,-2858,9117,-1962,90,7680,10823,18871,-10720,5703,-5517,-2342,-17434,13608,-16006,645,6914,9605,-19272,-6712,11509,-11601,-6944,15717,-12182,-12212,17132,18764,};
        int num[] = new int[]{5,2,3,1};
        Long start = System.currentTimeMillis();
        int[] sortArray = insertionSort.insertSort(num);
        Long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start) + "ms");
        String str = Arrays.stream(sortArray).mapToObj(Objects::toString).collect(Collectors.joining(","));
        System.out.println(str);
    }


}
