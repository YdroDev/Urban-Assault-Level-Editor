package main;

import UAstructures.UAitem;

import java.util.ArrayList;

public class UAdata {

	public static ArrayList<UAitem> resUnits = new ArrayList<UAitem>();
	public static ArrayList<UAitem> ghorUnits = new ArrayList<UAitem>();
	public static ArrayList<UAitem> taerUnits = new ArrayList<UAitem>();
	public static ArrayList<UAitem> mykoUnits = new ArrayList<UAitem>();
	public static ArrayList<UAitem> sulgUnits = new ArrayList<UAitem>();
	public static ArrayList<UAitem> trainingUnits = new ArrayList<UAitem>();
	public static ArrayList<UAitem> specialUnits = new ArrayList<UAitem>();
	public static ArrayList<UAitem> allUnits = new ArrayList<UAitem>();
	public static ArrayList<UAitem> techUpgradeItems = new ArrayList<UAitem>();
	public static ArrayList<UAitem> fallbackUnits = new ArrayList<UAitem>();
	
	public static ArrayList<UAitem> resBuildings = new ArrayList<UAitem>();
	public static ArrayList<UAitem> ghorBuildings = new ArrayList<UAitem>();
	public static ArrayList<UAitem> taerBuildings = new ArrayList<UAitem>();
	public static ArrayList<UAitem> mykoBuildings = new ArrayList<UAitem>();
	public static ArrayList<UAitem> blackSectBuildings = new ArrayList<UAitem>();
	public static ArrayList<UAitem> miscBuildings = new ArrayList<UAitem>();
	public static ArrayList<UAitem> allBuildings = new ArrayList<UAitem>();
	public static ArrayList<UAitem> fallbackBuildings = new ArrayList<UAitem>();
	
	public static void addOriginalData() {
		clearData();
		resUnits.add(new UAitem().setID(1).setName("Weasel").setIconName("weasel").setMapIcon(0));
		resUnits.add(new UAitem().setID(16).setName("Fox").setIconName("fox").setMapIcon(0));
		resUnits.add(new UAitem().setID(2).setName("Jaguar").setIconName("jaguar").setMapIcon(0));
		resUnits.add(new UAitem().setID(3).setName("Tiger").setIconName("tiger").setMapIcon(0));
		resUnits.add(new UAitem().setID(12).setName("Rhino").setIconName("rhino").setMapIcon(0));
		resUnits.add(new UAitem().setID(11).setName("Rock-Sled").setIconName("rockSled").setMapIcon(0));
		resUnits.add(new UAitem().setID(10).setName("Firefly").setIconName("firefly").setMapIcon(1));
		resUnits.add(new UAitem().setID(6).setName("Wasp").setIconName("wasp").setMapIcon(1));
		resUnits.add(new UAitem().setID(15).setName("Hornet").setIconName("hornet").setMapIcon(1));
		resUnits.add(new UAitem().setID(14).setName("Dragonfly").setIconName("dragonfly").setMapIcon(1));
		resUnits.add(new UAitem().setID(4).setName("Falcon").setIconName("falcon").setMapIcon(2));
		resUnits.add(new UAitem().setID(7).setName("Warhammer").setIconName("warhammer").setMapIcon(2));
		resUnits.add(new UAitem().setID(5).setName("Marauder").setIconName("marauder").setMapIcon(2));
		resUnits.add(new UAitem().setID(9).setName("Scout").setIconName("scout").setMapIcon(3));
		resUnits.add(new UAitem().setID(133).setName("Giant(Blue)").setIconName("blueGiant").setMapIcon(2));
		resUnits.add(new UAitem().setID(134).setName("Bronsteijn(Blue)").setIconName("blueBronsteijn").setMapIcon(2));
		
		resBuildings.add(new UAitem().setID(63).setName("Power Station 1").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/resEnergy1.png"));
		resBuildings.add(new UAitem().setID(1).setName("Power Station 1+").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/resEnergy2.png"));
		resBuildings.add(new UAitem().setID(11).setName("Power Station 2").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/resEnergy3.png"));
		resBuildings.add(new UAitem().setID(64).setName("Power Station 2+").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/resEnergy4.png"));
		resBuildings.add(new UAitem().setID(28).setName("Flak Station 1").setIconName("flakStation").setTypMapID(205).setImg("/img/blg_map/resFlak.png"));
		resBuildings.add(new UAitem().setID(2).setName("Flak Station 2").setIconName("flakStation").setTypMapID(200).setImg("/img/blg_map/resFlak.png"));
		resBuildings.add(new UAitem().setID(3).setName("Radar Station 1").setIconName("radarStation").setTypMapID(204).setImg("/img/blg_map/resRadar.png"));
		resBuildings.add(new UAitem().setID(54).setName("Radar Station 2").setIconName("radarStation").setTypMapID(204).setImg("/img/blg_map/resRadar.png"));
		
		ghorUnits.add(new UAitem().setID(22).setName("Speedy").setIconName("speedy").setMapIcon(0));
		ghorUnits.add(new UAitem().setID(26).setName("Tekh-Trakh").setIconName("tekhTrakh").setMapIcon(0));
		ghorUnits.add(new UAitem().setID(24).setName("Ghagoil 1").setIconName("ghagoil1").setMapIcon(1));
		ghorUnits.add(new UAitem().setID(28).setName("Ghagoil 2").setIconName("ghagoil2").setMapIcon(1));
		ghorUnits.add(new UAitem().setID(25).setName("Ghagoil 3").setIconName("ghagoil3").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(23).setName("Ying").setIconName("ying").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(31).setName("Ting-Ying").setIconName("tingYing").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(130).setName("Yang").setIconName("yang").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(27).setName("Giant").setIconName("giant").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(30).setName("Giant 2").setIconName("giant").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(29).setName("Ormu").setIconName("ormu").setMapIcon(3));
		
		ghorBuildings.add(new UAitem().setID(52).setName("KYTPOWER").setIconName("powerStation").setTypMapID(15).setImg("/img/blg_map/ghorEnergy1.png"));
		ghorBuildings.add(new UAitem().setID(12).setName("KytPower").setIconName("powerStation").setTypMapID(15).setImg("/img/blg_map/ghorEnergy2.png"));
		ghorBuildings.add(new UAitem().setID(30).setName("KYTFLAK").setIconName("flakStation").setTypMapID(207).setImg("/img/blg_map/ghorFlak.png"));
		ghorBuildings.add(new UAitem().setID(71).setName("Kyt Radar Station").setIconName("radarStation").setTypMapID(240).setImg("/img/blg_map/ghorRadar.png"));
		
		taerUnits.add(new UAitem().setID(32).setName("Eisenhans").setIconName("eisenhans").setMapIcon(0));
		taerUnits.add(new UAitem().setID(37).setName("Leonid").setIconName("leonid").setMapIcon(0));
		taerUnits.add(new UAitem().setID(33).setName("Hetzel").setIconName("hetzel").setMapIcon(2));
		taerUnits.add(new UAitem().setID(8).setName("Phantom").setIconName("phantom").setMapIcon(2));
		taerUnits.add(new UAitem().setID(36).setName("Serp").setIconName("serp").setMapIcon(2));
		taerUnits.add(new UAitem().setID(38).setName("Mnosjetz").setIconName("mnosjetz").setMapIcon(2));
		taerUnits.add(new UAitem().setID(131).setName("Zeppelin").setIconName("zeppelin").setMapIcon(2));
		taerUnits.add(new UAitem().setID(34).setName("Bronsteijn").setIconName("bronsteijn").setMapIcon(1));
		taerUnits.add(new UAitem().setID(35).setName("Otschko").setIconName("otschko").setMapIcon(3));
		
		taerBuildings.add(new UAitem().setID(53).setName("TAER POWR").setIconName("powerStation").setTypMapID(14).setImg("/img/blg_map/taerEnergy1.png"));
		taerBuildings.add(new UAitem().setID(17).setName("TaerPower").setIconName("powerStation").setTypMapID(14).setImg("/img/blg_map/taerEnergy2.png"));
		taerBuildings.add(new UAitem().setID(31).setName("TAERFLAK").setIconName("flakStation").setTypMapID(208).setImg("/img/blg_map/taerFlak.png"));
		taerBuildings.add(new UAitem().setID(73).setName("Taer Radar Station").setIconName("radarStation").setTypMapID(239).setImg("/img/blg_map/taerRadar.png"));
		
		mykoUnits.add(new UAitem().setID(65).setName("5PO-Prisma").setIconName("prisma").setMapIcon(1));
		mykoUnits.add(new UAitem().setID(64).setName("X01-Quadda").setIconName("quadda").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(66).setName("Static").setIconName("static").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(68).setName("0815 Ground").setIconName("ground").setMapIcon(0));
		mykoUnits.add(new UAitem().setID(63).setName("Hourglass").setIconName("hourglass").setMapIcon(1));
		mykoUnits.add(new UAitem().setID(69).setName("Airstick").setIconName("airstick").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(70).setName("Bomber").setIconName("bomber").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(67).setName("Radar").setIconName("radar").setMapIcon(3));
		
		mykoBuildings.add(new UAitem().setID(10).setName("MykoPower").setIconName("powerStation").setTypMapID(229).setImg("/img/blg_map/mykoEnergy1.png"));
		mykoBuildings.add(new UAitem().setID(13).setName("MykoFlak").setIconName("flakStation").setTypMapID(228).setImg("/img/blg_map/mykoFlak.png"));
		mykoBuildings.add(new UAitem().setID(72).setName("Myk Radar Station").setIconName("radarStation").setTypMapID(241).setImg("/img/blg_map/mykoRadar.png"));
		
		sulgUnits.add(new UAitem().setID(73).setName("Blue Spore").setIconName("blueSpore").setMapIcon(2));
		sulgUnits.add(new UAitem().setID(71).setName("Mean Green").setIconName("meanGreen").setMapIcon(2));
		sulgUnits.add(new UAitem().setID(72).setName("Slime Lord").setIconName("slimeLord").setMapIcon(2));
		sulgUnits.add(new UAitem().setID(74).setName("Little Brother").setIconName("littleBrother").setMapIcon(3));
		
		blackSectBuildings.add(new UAitem().setID(18).setName("Flakstation").setIconName("flakStation").setTypMapID(200).setImg("/img/blg_map/blasecFlak.png"));
		
		trainingUnits.add(new UAitem().setID(138).setName("Target Drone").setIconName("targetDrone").setMapIcon(2));
		trainingUnits.add(new UAitem().setID(142).setName("Target Drone 2").setIconName("targetDrone").setMapIcon(2));
		
		specialUnits.add(new UAitem().setID(13).setName("Evilfurz").setIconName("evilfurz").setMapIcon(0));
		
		miscBuildings.add(new UAitem().setID(8).setName("GRASFLAK2").setIconName("flakStation").setTypMapID(79).setImg("/img/blg_map/GRASFLAK2.png"));
		miscBuildings.add(new UAitem().setID(14).setName("KraftwerkOBL").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/kraftwerkOBL.png"));
		miscBuildings.add(new UAitem().setID(19).setName("Bodenflak").setIconName("flakStation").setTypMapID(1).setImg("/img/blg_map/bodenflak.png"));
		miscBuildings.add(new UAitem().setID(20).setName("GFLAK-OW").setIconName("flakStation").setTypMapID(199).setImg("/img/blg_map/gflak-ow.png"));
		miscBuildings.add(new UAitem().setID(21).setName("GFLAK-NS").setIconName("flakStation").setTypMapID(198).setImg("/img/blg_map/gflak-ns.png"));
		miscBuildings.add(new UAitem().setID(62).setName("STONEHENGE").setIconName("powerStation").setTypMapID(96).setImg("/img/blg_map/stonehenge.png"));
		miscBuildings.add(new UAitem().setID(29).setName("STIMMG1").setIconName("flakStation").setTypMapID(5).setImg("/img/blg_map/stimmg1.png"));
		miscBuildings.add(new UAitem().setID(33).setName("STIMMG2").setIconName("flakStation").setTypMapID(7).setImg("/img/blg_map/stimmg2.png"));
		miscBuildings.add(new UAitem().setID(34).setName("STIMMG3").setIconName("flakStation").setTypMapID(17).setImg("/img/blg_map/stimmg3.png"));

		techUpgradeItems.add(new UAitem().setID(90).setName("Resistance flak 1"));
		techUpgradeItems.add(new UAitem().setID(91).setName("Resistance flak 2"));
		techUpgradeItems.add(new UAitem().setID(92).setName("Resistance flak 3"));
		techUpgradeItems.add(new UAitem().setID(93).setName("Resistance flak 4"));
		techUpgradeItems.add(new UAitem().setID(83).setName("Ghorkov Small Flak"));
		techUpgradeItems.add(new UAitem().setID(77).setName("Ghorkov Big Flak"));
		techUpgradeItems.add(new UAitem().setID(87).setName("Taerkasten Flak"));

		fallbackUnits.add(new UAitem().setID(144).setName("Ostwind").setIconName("ostwind").setMapIcon(0));
		fallbackUnits.add(new UAitem().setID(143).setName("Thor's Hammer").setIconName("thorshammer").setMapIcon(0));
		fallbackUnits.add(new UAitem().setID(145).setName("Crusher").setIconName("crusher").setMapIcon(2));
		fallbackUnits.add(new UAitem().setID(189).setName("Little ground brother").setIconName("meanGreen").setMapIcon(0));
		
		fallbackBuildings.add(new UAitem().setID(22).setName("BODENFLAK2").setIconName("flakStation").setTypMapID(12).setImg("/img/blg_map/GRASFLAK2.png"));
		fallbackBuildings.add(new UAitem().setID(23).setName("BODENFLAK3").setIconName("flakStation").setTypMapID(13).setImg("/img/blg_map/bodenflak.png"));
		fallbackBuildings.add(new UAitem().setID(24).setName("BODENFLAK4").setIconName("flakStation").setTypMapID(32).setImg("/img/blg_map/bodenflak4.png"));
		fallbackBuildings.add(new UAitem().setID(55).setName("STEFANS FLAK KREU").setIconName("flakStation").setTypMapID(140).setImg("/img/blg_map/GRASFLAK2.png"));
		fallbackBuildings.add(new UAitem().setID(76).setName("KytPower 2").setIconName("powerStation").setTypMapID(15).setImg("/img/blg_map/ghorEnergy3.png"));
		fallbackBuildings.add(new UAitem().setID(80).setName("TaerPower2").setIconName("powerStation").setTypMapID(14).setImg("/img/blg_map/taerEnergy3.png"));

		addAllData();
	}
	
	public static void addMetropolisDawnData() {
		clearData();
		resUnits.add(new UAitem().setID(1).setName("Weasel").setIconName("weasel").setMapIcon(0));
		resUnits.add(new UAitem().setID(16).setName("Fox").setIconName("fox").setMapIcon(0));
		resUnits.add(new UAitem().setID(2).setName("Jaguar").setIconName("jaguar").setMapIcon(0));
		resUnits.add(new UAitem().setID(3).setName("Tiger").setIconName("tiger").setMapIcon(0));
		resUnits.add(new UAitem().setID(12).setName("Rhino").setIconName("rhino").setMapIcon(0));
		resUnits.add(new UAitem().setID(11).setName("Rock-Sled").setIconName("rockSled").setMapIcon(0));
		resUnits.add(new UAitem().setID(10).setName("Firefly").setIconName("firefly").setMapIcon(1));
		resUnits.add(new UAitem().setID(6).setName("Wasp").setIconName("wasp").setMapIcon(1));
		resUnits.add(new UAitem().setID(15).setName("Hornet").setIconName("hornet").setMapIcon(1));
		resUnits.add(new UAitem().setID(14).setName("Dragonfly").setIconName("dragonfly").setMapIcon(1));
		resUnits.add(new UAitem().setID(4).setName("Falcon").setIconName("falcon").setMapIcon(2));
		resUnits.add(new UAitem().setID(7).setName("Warhammer").setIconName("warhammer").setMapIcon(2));
		resUnits.add(new UAitem().setID(5).setName("Marauder").setIconName("marauder").setMapIcon(2));
		resUnits.add(new UAitem().setID(9).setName("Scout").setIconName("scout").setMapIcon(3));
		resUnits.add(new UAitem().setID(133).setName("Giant(Blue)").setIconName("blueGiant").setMapIcon(2));
		resUnits.add(new UAitem().setID(134).setName("Bronsteijn(Blue)").setIconName("blueBronsteijn").setMapIcon(2));
		
		resBuildings.add(new UAitem().setID(63).setName("Power Station 1").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/resEnergy1.png"));
		resBuildings.add(new UAitem().setID(1).setName("Power Station 1+").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/resEnergy2.png"));
		resBuildings.add(new UAitem().setID(11).setName("Power Station 2").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/resEnergy3.png"));
		resBuildings.add(new UAitem().setID(64).setName("Power Station 2+").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/resEnergy4.png"));
		resBuildings.add(new UAitem().setID(28).setName("Flak Station 1").setIconName("flakStation").setTypMapID(205).setImg("/img/blg_map/resFlak.png"));
		resBuildings.add(new UAitem().setID(2).setName("Flak Station 2").setIconName("flakStation").setTypMapID(200).setImg("/img/blg_map/resFlak.png"));
		resBuildings.add(new UAitem().setID(3).setName("Radar Station 1").setIconName("radarStation").setTypMapID(204).setImg("/img/blg_map/resRadar.png"));
		resBuildings.add(new UAitem().setID(54).setName("Radar Station 2").setIconName("radarStation").setTypMapID(204).setImg("/img/blg_map/resRadar.png"));
		
		ghorUnits.add(new UAitem().setID(22).setName("Speedy").setIconName("speedy").setMapIcon(0));
		ghorUnits.add(new UAitem().setID(26).setName("Tekh-Trakh").setIconName("tekhTrakh").setMapIcon(0));
		ghorUnits.add(new UAitem().setID(24).setName("Ghagoil 1").setIconName("ghagoil1").setMapIcon(1));
		ghorUnits.add(new UAitem().setID(28).setName("Ghagoil 2").setIconName("ghagoil2").setMapIcon(1));
		ghorUnits.add(new UAitem().setID(25).setName("Ghagoil 3").setIconName("ghagoil3").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(23).setName("Ying").setIconName("ying").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(31).setName("Ting-Ying").setIconName("tingYing").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(130).setName("Yang").setIconName("yang").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(27).setName("Giant").setIconName("giant").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(30).setName("Giant 2").setIconName("giant").setMapIcon(2));
		ghorUnits.add(new UAitem().setID(29).setName("Ormu").setIconName("ormu").setMapIcon(3));
		
		ghorBuildings.add(new UAitem().setID(52).setName("KYTPOWER").setIconName("powerStation").setTypMapID(15).setImg("/img/blg_map/ghorEnergy1.png"));
		ghorBuildings.add(new UAitem().setID(12).setName("KytPower").setIconName("powerStation").setTypMapID(15).setImg("/img/blg_map/ghorEnergy2.png"));
		ghorBuildings.add(new UAitem().setID(30).setName("KYTFLAK").setIconName("flakStation").setTypMapID(207).setImg("/img/blg_map/ghorFlak.png"));
		ghorBuildings.add(new UAitem().setID(71).setName("Kyt Radar Station").setIconName("radarStation").setTypMapID(240).setImg("/img/blg_map/ghorRadar.png"));
		
		taerUnits.add(new UAitem().setID(32).setName("Eisenhans").setIconName("eisenhans").setMapIcon(0));
		taerUnits.add(new UAitem().setID(144).setName("Ostwind").setIconName("ostwind").setMapIcon(0));
		taerUnits.add(new UAitem().setID(37).setName("Leonid").setIconName("leonid").setMapIcon(0));
		taerUnits.add(new UAitem().setID(143).setName("Thor's Hammer").setIconName("thorshammer").setMapIcon(0));
		taerUnits.add(new UAitem().setID(33).setName("Hetzel").setIconName("hetzel").setMapIcon(2));
		taerUnits.add(new UAitem().setID(8).setName("Phantom").setIconName("phantom").setMapIcon(2));
		taerUnits.add(new UAitem().setID(36).setName("Serp").setIconName("serp").setMapIcon(2));
		taerUnits.add(new UAitem().setID(38).setName("Mnosjetz").setIconName("mnosjetz").setMapIcon(2));
		taerUnits.add(new UAitem().setID(131).setName("Zeppelin").setIconName("zeppelin").setMapIcon(2));
		taerUnits.add(new UAitem().setID(34).setName("Bronsteijn").setIconName("bronsteijn").setMapIcon(1));
		taerUnits.add(new UAitem().setID(35).setName("Otschko").setIconName("otschko").setMapIcon(3));
		
		taerBuildings.add(new UAitem().setID(53).setName("TAER POWR").setIconName("powerStation").setTypMapID(14).setImg("/img/blg_map/taerEnergy1.png"));
		taerBuildings.add(new UAitem().setID(17).setName("TaerPower").setIconName("powerStation").setTypMapID(14).setImg("/img/blg_map/taerEnergy2.png"));
		taerBuildings.add(new UAitem().setID(31).setName("TAERFLAK").setIconName("flakStation").setTypMapID(208).setImg("/img/blg_map/taerFlak.png"));
		taerBuildings.add(new UAitem().setID(74).setName("Flak station").setIconName("flakStation").setTypMapID(208).setImg("/img/blg_map/taerFlak.png"));
		taerBuildings.add(new UAitem().setID(73).setName("Taer Radar Station").setIconName("radarStation").setTypMapID(239).setImg("/img/blg_map/taerRadar.png"));
		
		mykoUnits.add(new UAitem().setID(65).setName("5PO-Prisma").setIconName("prisma").setMapIcon(1));
		mykoUnits.add(new UAitem().setID(64).setName("X01-Quadda").setIconName("quadda").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(66).setName("Static").setIconName("static").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(68).setName("0815 Ground").setIconName("ground").setMapIcon(0));
		mykoUnits.add(new UAitem().setID(63).setName("Hourglass").setIconName("hourglass").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(145).setName("Crusher").setIconName("crusher").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(69).setName("Airstick").setIconName("airstick").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(70).setName("Bomber").setIconName("bomber").setMapIcon(2));
		mykoUnits.add(new UAitem().setID(67).setName("Radar").setIconName("radar").setMapIcon(3));
		
		mykoBuildings.add(new UAitem().setID(10).setName("MykoPower").setIconName("powerStation").setTypMapID(229).setImg("/img/blg_map/mykoEnergy1.png"));
		mykoBuildings.add(new UAitem().setID(13).setName("MykoFlak").setIconName("flakStation").setTypMapID(228).setImg("/img/blg_map/mykoFlak.png"));
		mykoBuildings.add(new UAitem().setID(72).setName("Myk Radar Station").setIconName("radarStation").setTypMapID(241).setImg("/img/blg_map/mykoRadar.png"));
		
		sulgUnits.add(new UAitem().setID(73).setName("Blue Spore").setIconName("blueSpore").setMapIcon(2));
		sulgUnits.add(new UAitem().setID(71).setName("Mean Green").setIconName("meanGreen").setMapIcon(2));
		sulgUnits.add(new UAitem().setID(72).setName("Slime Lord").setIconName("slimeLord").setMapIcon(2));
		sulgUnits.add(new UAitem().setID(74).setName("Little Brother").setIconName("littleBrother").setMapIcon(3));
		
		blackSectBuildings.add(new UAitem().setID(18).setName("Flakstation").setIconName("flakStation").setTypMapID(200).setImg("/img/blg_map/blasecFlak.png"));
		
		trainingUnits.add(new UAitem().setID(138).setName("Target Drone").setIconName("targetDrone").setMapIcon(2));
		trainingUnits.add(new UAitem().setID(142).setName("Target Drone 2").setIconName("targetDrone").setMapIcon(2));
		
		specialUnits.add(new UAitem().setID(13).setName("Evilfurz").setIconName("evilfurz").setMapIcon(0));
		
		miscBuildings.add(new UAitem().setID(8).setName("GRASFLAK2").setIconName("flakStation").setTypMapID(79).setImg("/img/blg_map/GRASFLAK2.png"));
		miscBuildings.add(new UAitem().setID(14).setName("KraftwerkOBL").setIconName("powerStation").setTypMapID(201).setImg("/img/blg_map/kraftwerkOBL.png"));
		miscBuildings.add(new UAitem().setID(19).setName("Bodenflak").setIconName("flakStation").setTypMapID(1).setImg("/img/blg_map/bodenflak.png"));
		miscBuildings.add(new UAitem().setID(20).setName("GFLAK-OW").setIconName("flakStation").setTypMapID(199).setImg("/img/blg_map/gflak-ow.png"));
		miscBuildings.add(new UAitem().setID(21).setName("GFLAK-NS").setIconName("flakStation").setTypMapID(198).setImg("/img/blg_map/gflak-ns.png"));
		miscBuildings.add(new UAitem().setID(62).setName("STONEHENGE").setIconName("powerStation").setTypMapID(96).setImg("/img/blg_map/stonehenge.png"));
		miscBuildings.add(new UAitem().setID(29).setName("STIMMG1").setIconName("flakStation").setTypMapID(5).setImg("/img/blg_map/stimmg1.png"));
		miscBuildings.add(new UAitem().setID(33).setName("STIMMG2").setIconName("flakStation").setTypMapID(7).setImg("/img/blg_map/stimmg2.png"));
		miscBuildings.add(new UAitem().setID(34).setName("STIMMG3").setIconName("flakStation").setTypMapID(17).setImg("/img/blg_map/stimmg3.png"));
		miscBuildings.add(new UAitem().setID(22).setName("BODENFLAK2").setIconName("flakStation").setTypMapID(12).setImg("/img/blg_map/GRASFLAK2.png"));
		miscBuildings.add(new UAitem().setID(23).setName("BODENFLAK3").setIconName("flakStation").setTypMapID(13).setImg("/img/blg_map/bodenflak.png"));
		miscBuildings.add(new UAitem().setID(24).setName("BODENFLAK4").setIconName("flakStation").setTypMapID(32).setImg("/img/blg_map/bodenflak4.png"));
		miscBuildings.add(new UAitem().setID(55).setName("STEFANS FLAK KREU").setIconName("flakStation").setTypMapID(140).setImg("/img/blg_map/GRASFLAK2.png"));

		techUpgradeItems.add(new UAitem().setID(40).setName("Ostwind weapon"));
		techUpgradeItems.add(new UAitem().setID(90).setName("Resistance flak 1"));
		techUpgradeItems.add(new UAitem().setID(91).setName("Resistance flak 2"));
		techUpgradeItems.add(new UAitem().setID(92).setName("Resistance flak 3"));
		techUpgradeItems.add(new UAitem().setID(93).setName("Resistance flak 4"));
		techUpgradeItems.add(new UAitem().setID(83).setName("Ghorkov Small Flak"));
		techUpgradeItems.add(new UAitem().setID(77).setName("Ghorkov Big Flak"));
		techUpgradeItems.add(new UAitem().setID(87).setName("Taerkasten Flak"));

		fallbackUnits.add(new UAitem().setID(189).setName("Little ground brother").setIconName("meanGreen").setMapIcon(0));
		
		fallbackBuildings.add(new UAitem().setID(76).setName("KytPower 2").setIconName("powerStation").setTypMapID(15).setImg("/img/blg_map/ghorEnergy3.png"));
		fallbackBuildings.add(new UAitem().setID(80).setName("TaerPower2").setIconName("powerStation").setTypMapID(14).setImg("/img/blg_map/taerEnergy3.png"));
		addAllData();
	}
	
	public static void clearData() {
		resUnits.clear();
		ghorUnits.clear();
		taerUnits.clear();
		mykoUnits.clear();
		sulgUnits.clear();
		trainingUnits.clear();
		specialUnits.clear();
		allUnits.clear();
		techUpgradeItems.clear();
		fallbackUnits.clear();
		
		resBuildings.clear();
		ghorBuildings.clear();
		taerBuildings.clear();
		mykoBuildings.clear();
		blackSectBuildings.clear();
		miscBuildings.clear();
		allBuildings.clear();
		fallbackBuildings.clear();
	}
	
	static void addAllData() {
		for(UAitem resUnit : resUnits) {
			allUnits.add(resUnit);
		}
		for(UAitem ghorUnit : ghorUnits) {
			allUnits.add(ghorUnit);
		}
		for(UAitem taerUnit : taerUnits) {
			allUnits.add(taerUnit);
		}
		for(UAitem mykoUnit : mykoUnits) {
			allUnits.add(mykoUnit);
		}
		for(UAitem sulgUnit : sulgUnits) {
			allUnits.add(sulgUnit);
		}
		for(UAitem trainingUnit : trainingUnits) {
			allUnits.add(trainingUnit);
		}
		for(UAitem specialUnit : specialUnits) {
			allUnits.add(specialUnit);
		}
		
		for(UAitem resBuilding : resBuildings) {
			allBuildings.add(resBuilding);
		}
		for(UAitem ghorBuilding : ghorBuildings) {
			allBuildings.add(ghorBuilding);
		}
		for(UAitem taerBuilding : taerBuildings) {
			allBuildings.add(taerBuilding);
		}
		for(UAitem mykoBuilding : mykoBuildings) {
			allBuildings.add(mykoBuilding);
		}
		for(UAitem blackSectBuilding : blackSectBuildings) {
			allBuildings.add(blackSectBuilding);
		}
		for(UAitem miscBuilding : miscBuildings) {
			allBuildings.add(miscBuilding);
		}
	}

	public static UAitem getUnit(int id) {
		for(UAitem unit : allUnits){
			if(unit.getID() == id){
				return unit;
			}
		}
		for(UAitem unit : fallbackUnits){
			if(unit.getID() == id){
				return unit;
			}
		}
		for(UAitem unit : techUpgradeItems){
			if(unit.getID() == id){
				return unit;
			}
		}
		return null;
	}

	public static UAitem getBuilding(int id){
		for(UAitem building : allBuildings){
			if(building.getID() == id){
				return building;
			}
		}
		for(UAitem building : fallbackBuildings){
			if(building.getID() == id){
				return building;
			}
		}
		return null;
	}

	public static int getUnitIDfromName(String name) {
		for(UAitem unit : allUnits) {
			if(unit.getName() == name)
				return unit.getID();
		}
		for(UAitem unit : fallbackUnits) {
			if(unit.getName() == name)
				return unit.getID();
		}
		return -1;
	}
	public static int getBuildingIDfromName(String name) {
		for(UAitem building : allBuildings) {
			if(building.getName() == name)
				return building.getID();
		}
		for(UAitem building : fallbackBuildings) {
			if(building.getName() == name)
				return building.getID();
		}
		return -1;
	}
	public static int getTypMapfromName(String name) {
		for(UAitem building : allBuildings) {
			if(building.getName() == name)
				return building.getTypMapID();
		}
		return 0;
	}
}
