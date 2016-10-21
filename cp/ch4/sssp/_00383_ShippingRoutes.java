package helvidios.cp.ch4.sssp;

import java.util.*;
import static java.lang.Math.*;

public class _00383_ShippingRoutes{
	public static void main(String[] args){
		String data = "2\n" +
				"6 7 5\n" +
				"AA CC QR FF DD AB\n" +
				"AA CC\n" +
				"CC QR\n" +
				"DD CC\n" +
				"AA DD\n" +
				"AA AB\n" +
				"DD QR\n" +
				"AB DD\n" +
				"5 AA AB\n" +
				"14 DD CC\n" +
				"1 CC DD\n" +
				"2 AA FF\n" +
				"13 AB QR\n" +
				"3 0 1\n" +
				"AA BB CC\n" +
				"5 AA CC";
		String data2 = "10\n" +
				"3 1 10\n" +
				"OO HW KT\n" +
				"KT OO\n" +
				"11 OO KT\n" +
				"9 HW OO\n" +
				"9 HW KT\n" +
				"8 HW OO\n" +
				"7 KT HW\n" +
				"14 HW KT\n" +
				"4 KT OO\n" +
				"16 HW OO\n" +
				"6 OO KT\n" +
				"1 HW OO\n" +
				"29 216 1\n" +
				"DU IM HI RO WX KF KD MV HJ MC NC FD UG RT HX BU MI TB QW NL XV AD SH MU XW XB UQ NV TG\n" +
				"BU FD\n" +
				"UG HX\n" +
				"XB HX\n" +
				"NC NV\n" +
				"MC HI\n" +
				"RT MV\n" +
				"SH MV\n" +
				"DU WX\n" +
				"RT UQ\n" +
				"SH KF\n" +
				"NC RO\n" +
				"HX DU\n" +
				"XV TB\n" +
				"KD DU\n" +
				"MC KF\n" +
				"TG XB\n" +
				"DU BU\n" +
				"NL RO\n" +
				"MV XB\n" +
				"HI RT\n" +
				"IM XW\n" +
				"KD TG\n" +
				"KD NC\n" +
				"XV UG\n" +
				"MV HI\n" +
				"UG IM\n" +
				"NV NL\n" +
				"XB TB\n" +
				"BU NV\n" +
				"HJ MU\n" +
				"QW XW\n" +
				"HI BU\n" +
				"XV RT\n" +
				"FD HI\n" +
				"HX KF\n" +
				"UQ NV\n" +
				"MI DU\n" +
				"XB KD\n" +
				"MU HX\n" +
				"QW FD\n" +
				"KD HJ\n" +
				"QW HI\n" +
				"UG NL\n" +
				"WX QW\n" +
				"NV MU\n" +
				"UQ RO\n" +
				"NC KF\n" +
				"FD MV\n" +
				"BU MU\n" +
				"NL IM\n" +
				"DU MV\n" +
				"DU QW\n" +
				"BU KD\n" +
				"DU RO\n" +
				"KD MC\n" +
				"WX HJ\n" +
				"IM BU\n" +
				"XB QW\n" +
				"UG AD\n" +
				"TG MV\n" +
				"HJ KF\n" +
				"TB HI\n" +
				"NC IM\n" +
				"XB MC\n" +
				"HX HI\n" +
				"SH XW\n" +
				"NL XB\n" +
				"TG XW\n" +
				"UQ HI\n" +
				"NL NC\n" +
				"TB NC\n" +
				"HI XV\n" +
				"MC QW\n" +
				"WX FD\n" +
				"IM RT\n" +
				"NV AD\n" +
				"AD TB\n" +
				"UQ KF\n" +
				"MI MV\n" +
				"TB NL\n" +
				"WX TB\n" +
				"NV UG\n" +
				"XV BU\n" +
				"NC XW\n" +
				"NC XV\n" +
				"AD KD\n" +
				"IM TG\n" +
				"MU QW\n" +
				"HJ NL\n" +
				"WX RT\n" +
				"HX UQ\n" +
				"XW MC\n" +
				"TB IM\n" +
				"QW KD\n" +
				"KF RT\n" +
				"MI RT\n" +
				"TB RO\n" +
				"RO WX\n" +
				"MU UQ\n" +
				"HJ XW\n" +
				"XB RO\n" +
				"BU XW\n" +
				"RO TG\n" +
				"FD RT\n" +
				"NL KD\n" +
				"BU QW\n" +
				"MU RO\n" +
				"MI UQ\n" +
				"XB WX\n" +
				"KF IM\n" +
				"RO NV\n" +
				"MV HX\n" +
				"UQ SH\n" +
				"MI NV\n" +
				"QW NL\n" +
				"UG QW\n" +
				"DU UQ\n" +
				"DU NL\n" +
				"QW NC\n" +
				"RO MI\n" +
				"IM HI\n" +
				"TG HJ\n" +
				"MI NL\n" +
				"TB BU\n" +
				"KD TB\n" +
				"MC HX\n" +
				"TB UQ\n" +
				"TG HI\n" +
				"WX SH\n" +
				"AD XB\n" +
				"NV TB\n" +
				"XV MU\n" +
				"KF FD\n" +
				"UQ UG\n" +
				"HJ NC\n" +
				"FD DU\n" +
				"AD HX\n" +
				"AD IM\n" +
				"NV MC\n" +
				"MU IM\n" +
				"SH AD\n" +
				"UG WX\n" +
				"UG MC\n" +
				"HI WX\n" +
				"KF RO\n" +
				"HX FD\n" +
				"XW XB\n" +
				"HX XW\n" +
				"MV XW\n" +
				"NC MI\n" +
				"FD HJ\n" +
				"HX KD\n" +
				"XW XV\n" +
				"TB MC\n" +
				"MC BU\n" +
				"XW UG\n" +
				"KD XW\n" +
				"HI MU\n" +
				"MV KF\n" +
				"RO UG\n" +
				"MV MU\n" +
				"TB SH\n" +
				"WX AD\n" +
				"DU KF\n" +
				"TB UG\n" +
				"TG WX\n" +
				"NC MC\n" +
				"NC HI\n" +
				"RT SH\n" +
				"RO RT\n" +
				"IM SH\n" +
				"IM HJ\n" +
				"HX RT\n" +
				"KD NV\n" +
				"SH UG\n" +
				"HJ TB\n" +
				"WX IM\n" +
				"TB KF\n" +
				"KD RT\n" +
				"HI KF\n" +
				"NC MU\n" +
				"XW KF\n" +
				"FD XV\n" +
				"XB DU\n" +
				"MU XW\n" +
				"HJ AD\n" +
				"UQ XW\n" +
				"RT DU\n" +
				"NL MU\n" +
				"RT NV\n" +
				"AD HI\n" +
				"IM MC\n" +
				"NC DU\n" +
				"NV FD\n" +
				"RT UG\n" +
				"MI XV\n" +
				"MI XW\n" +
				"BU MI\n" +
				"XW FD\n" +
				"RT TB\n" +
				"QW HX\n" +
				"NL FD\n" +
				"MV NL\n" +
				"SH MI\n" +
				"UG TG\n" +
				"UQ XB\n" +
				"QW MV\n" +
				"KD UG\n" +
				"NV XB\n" +
				"MU KF\n" +
				"NL UQ\n" +
				"DU AD\n" +
				"TB TG\n" +
				"XB MU\n" +
				"DU NV\n" +
				"MC MU\n" +
				"9 QW IM\n" +
				"4 3 6\n" +
				"WG VQ ID XM\n" +
				"XM ID\n" +
				"XM VQ\n" +
				"ID VQ\n" +
				"2 VQ XM\n" +
				"17 WG ID\n" +
				"7 XM VQ\n" +
				"18 ID VQ\n" +
				"2 WG VQ\n" +
				"15 WG XM\n" +
				"4 6 3\n" +
				"SP KD EF WH\n" +
				"KD EF\n" +
				"WH EF\n" +
				"KD WH\n" +
				"SP EF\n" +
				"SP KD\n" +
				"WH SP\n" +
				"4 KD SP\n" +
				"12 SP EF\n" +
				"7 KD SP\n" +
				"3 2 7\n" +
				"XJ XY ZV\n" +
				"ZV XJ\n" +
				"XJ XY\n" +
				"6 XY XJ\n" +
				"9 XY ZV\n" +
				"20 XY XJ\n" +
				"10 XJ XY\n" +
				"18 ZV XJ\n" +
				"6 ZV XY\n" +
				"14 XJ ZV\n" +
				"10 29 4\n" +
				"AT AS NU ZY HQ KM LT MW ET EO\n" +
				"MW ZY\n" +
				"NU ET\n" +
				"AT KM\n" +
				"NU AT\n" +
				"MW AT\n" +
				"NU MW\n" +
				"EO AS\n" +
				"MW AS\n" +
				"HQ ZY\n" +
				"KM ET\n" +
				"LT AS\n" +
				"KM LT\n" +
				"AS KM\n" +
				"ET LT\n" +
				"LT ZY\n" +
				"AT LT\n" +
				"MW HQ\n" +
				"HQ KM\n" +
				"EO MW\n" +
				"MW KM\n" +
				"NU HQ\n" +
				"KM NU\n" +
				"ZY AT\n" +
				"EO LT\n" +
				"NU AS\n" +
				"EO ET\n" +
				"ET AS\n" +
				"HQ LT\n" +
				"ET ZY\n" +
				"7 AS HQ\n" +
				"12 NU KM\n" +
				"5 AT AS\n" +
				"11 NU LT\n" +
				"4 5 4\n" +
				"OK AD ZX XO\n" +
				"OK ZX\n" +
				"AD ZX\n" +
				"AD OK\n" +
				"XO OK\n" +
				"XO AD\n" +
				"4 XO AD\n" +
				"4 AD OK\n" +
				"14 OK ZX\n" +
				"10 ZX AD\n" +
				"14 58 10\n" +
				"MU YA PY FS EE SN JK ZB BI BZ IM WI GK LT\n" +
				"ZB IM\n" +
				"IM WI\n" +
				"MU ZB\n" +
				"GK BI\n" +
				"MU LT\n" +
				"JK MU\n" +
				"BZ BI\n" +
				"PY MU\n" +
				"WI JK\n" +
				"LT JK\n" +
				"YA IM\n" +
				"JK GK\n" +
				"EE YA\n" +
				"WI YA\n" +
				"GK YA\n" +
				"JK YA\n" +
				"FS PY\n" +
				"IM SN\n" +
				"IM LT\n" +
				"BI MU\n" +
				"BI JK\n" +
				"YA BI\n" +
				"BZ YA\n" +
				"BI FS\n" +
				"YA MU\n" +
				"SN PY\n" +
				"LT EE\n" +
				"BZ EE\n" +
				"YA FS\n" +
				"ZB EE\n" +
				"PY ZB\n" +
				"WI EE\n" +
				"BZ SN\n" +
				"GK PY\n" +
				"FS JK\n" +
				"GK IM\n" +
				"PY EE\n" +
				"IM MU\n" +
				"BZ IM\n" +
				"YA ZB\n" +
				"IM PY\n" +
				"IM FS\n" +
				"YA SN\n" +
				"GK LT\n" +
				"MU GK\n" +
				"BI LT\n" +
				"FS GK\n" +
				"BI SN\n" +
				"JK SN\n" +
				"BI WI\n" +
				"GK SN\n" +
				"IM JK\n" +
				"JK BZ\n" +
				"WI FS\n" +
				"EE MU\n" +
				"IM EE\n" +
				"ZB SN\n" +
				"SN FS\n" +
				"9 MU YA\n" +
				"9 PY IM\n" +
				"1 JK IM\n" +
				"9 BZ YA\n" +
				"9 JK ZB\n" +
				"10 BZ WI\n" +
				"7 EE MU\n" +
				"7 BI MU\n" +
				"7 GK LT\n" +
				"1 LT GK\n" +
				"25 171 0\n" +
				"YU FE NN ZA LZ DK OP CH GX IL EB BT RL BI PH SK SC AO QD JF UY QN UE UQ GP\n" +
				"BT QD\n" +
				"GX IL\n" +
				"RL FE\n" +
				"EB OP\n" +
				"EB NN\n" +
				"CH YU\n" +
				"AO QN\n" +
				"RL AO\n" +
				"OP QN\n" +
				"SC FE\n" +
				"IL QD\n" +
				"PH NN\n" +
				"GP UE\n" +
				"NN JF\n" +
				"BT QN\n" +
				"BT GX\n" +
				"RL BT\n" +
				"JF QD\n" +
				"UQ ZA\n" +
				"UQ UY\n" +
				"QN ZA\n" +
				"IL UY\n" +
				"AO DK\n" +
				"GX DK\n" +
				"GX AO\n" +
				"FE QD\n" +
				"NN SK\n" +
				"OP BI\n" +
				"QD ZA\n" +
				"GP QD\n" +
				"UE EB\n" +
				"LZ AO\n" +
				"CH OP\n" +
				"GX RL\n" +
				"FE SK\n" +
				"CH NN\n" +
				"PH YU\n" +
				"YU AO\n" +
				"PH FE\n" +
				"QD DK\n" +
				"BT JF\n" +
				"EB YU\n" +
				"UE UY\n" +
				"UQ DK\n" +
				"BI NN\n" +
				"GP UY\n" +
				"QN IL\n" +
				"NN IL\n" +
				"UE PH\n" +
				"SK ZA\n" +
				"QN UQ\n" +
				"CH QN\n" +
				"GP UQ\n" +
				"GX UQ\n" +
				"ZA FE\n" +
				"GX SK\n" +
				"AO ZA\n" +
				"BT UQ\n" +
				"SK BT\n" +
				"CH AO\n" +
				"BI BT\n" +
				"EB ZA\n" +
				"AO IL\n" +
				"EB GX\n" +
				"LZ UE\n" +
				"LZ IL\n" +
				"BI GP\n" +
				"GP LZ\n" +
				"NN YU\n" +
				"SC AO\n" +
				"YU SK\n" +
				"UE FE\n" +
				"OP AO\n" +
				"PH BI\n" +
				"OP YU\n" +
				"ZA GP\n" +
				"GP CH\n" +
				"UE YU\n" +
				"UE NN\n" +
				"UQ EB\n" +
				"NN UY\n" +
				"DK IL\n" +
				"SC GX\n" +
				"OP FE\n" +
				"DK QN\n" +
				"QD PH\n" +
				"NN GX\n" +
				"UQ RL\n" +
				"CH BI\n" +
				"ZA UY\n" +
				"LZ DK\n" +
				"SK OP\n" +
				"SC GP\n" +
				"RL EB\n" +
				"JF SC\n" +
				"OP UY\n" +
				"BI IL\n" +
				"UY AO\n" +
				"UY LZ\n" +
				"DK UE\n" +
				"QN BI\n" +
				"GP QN\n" +
				"NN RL\n" +
				"EB FE\n" +
				"QD NN\n" +
				"EB CH\n" +
				"UQ JF\n" +
				"UE QD\n" +
				"IL FE\n" +
				"ZA RL\n" +
				"IL CH\n" +
				"PH BT\n" +
				"FE JF\n" +
				"YU UY\n" +
				"UE QN\n" +
				"IL ZA\n" +
				"EB BI\n" +
				"NN LZ\n" +
				"UY SC\n" +
				"PH SC\n" +
				"OP LZ\n" +
				"OP DK\n" +
				"QN GX\n" +
				"IL OP\n" +
				"QN SC\n" +
				"DK SC\n" +
				"FE BI\n" +
				"SC LZ\n" +
				"QD GX\n" +
				"EB AO\n" +
				"YU GX\n" +
				"RL QD\n" +
				"EB GP\n" +
				"BT ZA\n" +
				"JF UE\n" +
				"BI SK\n" +
				"IL SK\n" +
				"NN GP\n" +
				"AO BT\n" +
				"AO UE\n" +
				"UQ IL\n" +
				"QD UQ\n" +
				"FE UY\n" +
				"UQ CH\n" +
				"JF YU\n" +
				"LZ ZA\n" +
				"FE QN\n" +
				"ZA GX\n" +
				"YU QD\n" +
				"NN ZA\n" +
				"OP JF\n" +
				"UY EB\n" +
				"BT YU\n" +
				"YU GP\n" +
				"LZ BT\n" +
				"UE IL\n" +
				"BT DK\n" +
				"FE LZ\n" +
				"SC RL\n" +
				"RL CH\n" +
				"UE GX\n" +
				"FE GX\n" +
				"EB SK\n" +
				"SK LZ\n" +
				"UY PH\n" +
				"ZA BI\n" +
				"SC IL\n" +
				"NN SC\n" +
				"DK ZA\n" +
				"GP OP\n" +
				"DK NN\n" +
				"22 218 4\n" +
				"CK YE VN HH OS FK GQ QY TU KX WK NR WF MB SF PG CA AA WI WE VM ER\n" +
				"ER KX\n" +
				"CA SF\n" +
				"MB NR\n" +
				"VM OS\n" +
				"VN CK\n" +
				"VM YE\n" +
				"WF PG\n" +
				"WI MB\n" +
				"AA ER\n" +
				"CA YE\n" +
				"WI WE\n" +
				"AA CK\n" +
				"CA WE\n" +
				"WI VM\n" +
				"YE VN\n" +
				"WE WF\n" +
				"YE OS\n" +
				"GQ CK\n" +
				"MB CA\n" +
				"TU MB\n" +
				"CK YE\n" +
				"WF ER\n" +
				"KX WI\n" +
				"CA TU\n" +
				"WE VN\n" +
				"AA CA\n" +
				"WF YE\n" +
				"VM VN\n" +
				"VM NR\n" +
				"OS TU\n" +
				"WI SF\n" +
				"WK ER\n" +
				"PG NR\n" +
				"WE YE\n" +
				"YE TU\n" +
				"WI CK\n" +
				"CK TU\n" +
				"SF NR\n" +
				"VM HH\n" +
				"OS QY\n" +
				"SF GQ\n" +
				"OS GQ\n" +
				"HH FK\n" +
				"ER NR\n" +
				"QY WK\n" +
				"CA WI\n" +
				"VN KX\n" +
				"OS WF\n" +
				"ER VN\n" +
				"HH KX\n" +
				"PG TU\n" +
				"VN SF\n" +
				"WF NR\n" +
				"OS VN\n" +
				"FK AA\n" +
				"MB SF\n" +
				"QY WF\n" +
				"QY CA\n" +
				"AA HH\n" +
				"WK YE\n" +
				"PG WK\n" +
				"YE ER\n" +
				"TU WF\n" +
				"WF CA\n" +
				"TU KX\n" +
				"KX QY\n" +
				"SF FK\n" +
				"YE MB\n" +
				"GQ QY\n" +
				"WK VM\n" +
				"KX YE\n" +
				"HH NR\n" +
				"GQ VN\n" +
				"CK SF\n" +
				"NR CK\n" +
				"PG ER\n" +
				"CA KX\n" +
				"HH CK\n" +
				"KX FK\n" +
				"VM WE\n" +
				"TU NR\n" +
				"WE CK\n" +
				"CA FK\n" +
				"VN NR\n" +
				"WI PG\n" +
				"FK WF\n" +
				"WI OS\n" +
				"ER HH\n" +
				"TU SF\n" +
				"FK VN\n" +
				"AA WK\n" +
				"HH WK\n" +
				"PG GQ\n" +
				"QY NR\n" +
				"QY VN\n" +
				"SF PG\n" +
				"HH VN\n" +
				"WK WI\n" +
				"GQ WK\n" +
				"YE WI\n" +
				"TU AA\n" +
				"PG YE\n" +
				"VN WK\n" +
				"SF YE\n" +
				"MB WF\n" +
				"GQ WI\n" +
				"WK OS\n" +
				"VM PG\n" +
				"TU HH\n" +
				"VM AA\n" +
				"CK VM\n" +
				"VM TU\n" +
				"AA YE\n" +
				"MB OS\n" +
				"CA ER\n" +
				"WK FK\n" +
				"PG KX\n" +
				"WK CA\n" +
				"NR AA\n" +
				"AA GQ\n" +
				"QY WE\n" +
				"AA OS\n" +
				"HH WF\n" +
				"CA PG\n" +
				"MB GQ\n" +
				"ER OS\n" +
				"ER FK\n" +
				"YE GQ\n" +
				"FK GQ\n" +
				"SF WF\n" +
				"MB WK\n" +
				"WE FK\n" +
				"KX GQ\n" +
				"WE PG\n" +
				"CA CK\n" +
				"WI NR\n" +
				"TU VN\n" +
				"CK OS\n" +
				"AA WI\n" +
				"WI TU\n" +
				"WK KX\n" +
				"VM FK\n" +
				"AA QY\n" +
				"FK WI\n" +
				"QY TU\n" +
				"YE NR\n" +
				"OS PG\n" +
				"WF CK\n" +
				"WK NR\n" +
				"VN CA\n" +
				"FK TU\n" +
				"SF AA\n" +
				"WF GQ\n" +
				"FK OS\n" +
				"HH SF\n" +
				"ER VM\n" +
				"KX NR\n" +
				"WF WI\n" +
				"WK SF\n" +
				"GQ NR\n" +
				"HH WI\n" +
				"MB QY\n" +
				"QY HH\n" +
				"SF ER\n" +
				"MB CK\n" +
				"MB VM\n" +
				"VM GQ\n" +
				"TU WK\n" +
				"KX MB\n" +
				"FK PG\n" +
				"NR WE\n" +
				"CK QY\n" +
				"PG VN\n" +
				"CK KX\n" +
				"PG MB\n" +
				"TU WE\n" +
				"PG HH\n" +
				"CK PG\n" +
				"KX VM\n" +
				"CK WK\n" +
				"CA OS\n" +
				"GQ CA\n" +
				"FK YE\n" +
				"WI VN\n" +
				"QY WI\n" +
				"WE MB\n" +
				"WF AA\n" +
				"ER WI\n" +
				"WF VM\n" +
				"CA NR\n" +
				"WK WE\n" +
				"QY VM\n" +
				"HH MB\n" +
				"KX WF\n" +
				"QY PG\n" +
				"CA HH\n" +
				"GQ HH\n" +
				"HH OS\n" +
				"GQ ER\n" +
				"HH YE\n" +
				"ER WE\n" +
				"OS SF\n" +
				"NR FK\n" +
				"CA VM\n" +
				"MB VN\n" +
				"OS WE\n" +
				"AA MB\n" +
				"WE SF\n" +
				"GQ TU\n" +
				"WE GQ\n" +
				"KX AA\n" +
				"CK ER\n" +
				"CK FK\n" +
				"FK QY\n" +
				"QY ER\n" +
				"MB FK\n" +
				"SF KX\n" +
				"WE AA\n" +
				"8 FK TU\n" +
				"5 KX WK\n" +
				"15 WF FK\n" +
				"7 KX VM\n";
		Scanner s = new Scanner(data2);
		int nTests = s.nextInt();
		System.out.println("SHIPPING ROUTES OUTPUT");
		for(int i = 0; i < nTests; i++){
			System.out.printf("\nDATA SET  %d\n\n", i + 1);
			int nWarehouses = s.nextInt();
			int nLegs = s.nextInt();
			int nRequests = s.nextInt();
			Map<String, Integer> map = new HashMap<>();
			Map<Integer, List<Integer>> graph = new HashMap<>();
			for(int j = 0; j < nWarehouses; j++){
				map.put(s.next(), j);
				graph.put(j, new ArrayList<>());
			}
			for(int j = 0; j < nLegs; j++){
				String w1 = s.next();
				String w2 = s.next();
				graph.get(map.get(w1)).add(map.get(w2));
				graph.get(map.get(w2)).add(map.get(w1));
			}
			for(int j = 0; j < nRequests; j++){
				int shipmentSize = s.nextInt();
				String source = s.next();
				String destination = s.next();
				bfs(graph, map.get(source));
				int minCost = shipmentSize * dist[map.get(destination)] * 100;
				if(minCost < 0) System.out.println("NO SHIPMENT POSSIBLE");
				else System.out.printf("$%d\n", minCost);
			}
		}
		System.out.println("\nEND OF OUTPUT");
	}

	static void getPath(int source, int target, List<Integer> path){
		if(source == target){
			path.add(source);
			return;
		}
		getPath(source, parent[target], path);
		path.add(target);
	}

	static final int INF = -1;
	static int[] dist;
	static int[] parent;
	static void bfs(Map<Integer, List<Integer>> graph, int source){
		dist = new int[graph.keySet().size()];
		for(int i = 0; i < dist.length; i++){
			dist[i] = INF;
		}
		dist[source] = 0;
		parent = new int[graph.keySet().size()];
		Queue<Integer> q = new LinkedList<>();
		q.add(source);
		while(!q.isEmpty()){
			int u = q.remove();
			for(int neighbor : graph.get(u)){
				if(dist[neighbor] == INF){
					dist[neighbor] = dist[u] + 1;
					parent[neighbor] = u;
					q.add(neighbor);
				}
			}
		}
	}
}