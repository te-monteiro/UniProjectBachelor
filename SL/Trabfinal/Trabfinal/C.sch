<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="XLXN_1" />
        <signal name="Qi" />
        <signal name="CLK" />
        <signal name="XLXN_4" />
        <signal name="CPLC" />
        <signal name="LOADC" />
        <signal name="Di" />
        <signal name="XLXN_9" />
        <signal name="XLXN_10" />
        <signal name="CLEARC" />
        <port polarity="Output" name="Qi" />
        <port polarity="Input" name="CLK" />
        <port polarity="Input" name="CPLC" />
        <port polarity="Input" name="LOADC" />
        <port polarity="Input" name="Di" />
        <port polarity="Input" name="CLEARC" />
        <blockdef name="m4_1e">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="96" y1="-416" y2="-416" x1="0" />
            <line x2="96" y1="-352" y2="-352" x1="0" />
            <line x2="96" y1="-288" y2="-288" x1="0" />
            <line x2="96" y1="-224" y2="-224" x1="0" />
            <line x2="96" y1="-32" y2="-32" x1="0" />
            <line x2="256" y1="-320" y2="-320" x1="320" />
            <line x2="96" y1="-160" y2="-160" x1="0" />
            <line x2="96" y1="-96" y2="-96" x1="0" />
            <line x2="96" y1="-96" y2="-96" x1="176" />
            <line x2="176" y1="-208" y2="-96" x1="176" />
            <line x2="96" y1="-32" y2="-32" x1="224" />
            <line x2="224" y1="-216" y2="-32" x1="224" />
            <line x2="96" y1="-224" y2="-192" x1="256" />
            <line x2="256" y1="-416" y2="-224" x1="256" />
            <line x2="256" y1="-448" y2="-416" x1="96" />
            <line x2="96" y1="-192" y2="-448" x1="96" />
            <line x2="96" y1="-160" y2="-160" x1="128" />
            <line x2="128" y1="-200" y2="-160" x1="128" />
        </blockdef>
        <blockdef name="vcc">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-64" x1="64" />
            <line x2="64" y1="0" y2="-32" x1="64" />
            <line x2="32" y1="-64" y2="-64" x1="96" />
        </blockdef>
        <blockdef name="fdr">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-256" y2="-256" x1="0" />
            <line x2="320" y1="-256" y2="-256" x1="384" />
            <line x2="64" y1="-32" y2="-32" x1="0" />
            <rect width="256" x="64" y="-320" height="256" />
            <line x2="192" y1="-64" y2="-32" x1="192" />
            <line x2="64" y1="-32" y2="-32" x1="192" />
            <line x2="80" y1="-112" y2="-128" x1="64" />
            <line x2="64" y1="-128" y2="-144" x1="80" />
        </blockdef>
        <blockdef name="inv">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-32" x1="0" />
            <line x2="160" y1="-32" y2="-32" x1="224" />
            <line x2="128" y1="-64" y2="-32" x1="64" />
            <line x2="64" y1="-32" y2="0" x1="128" />
            <line x2="64" y1="0" y2="-64" x1="64" />
            <circle r="16" cx="144" cy="-32" />
        </blockdef>
        <block symbolname="m4_1e" name="XLXI_2">
            <blockpin signalname="Qi" name="D0" />
            <blockpin signalname="Di" name="D1" />
            <blockpin signalname="XLXN_9" name="D2" />
            <blockpin name="D3" />
            <blockpin signalname="XLXN_4" name="E" />
            <blockpin signalname="LOADC" name="S0" />
            <blockpin signalname="CPLC" name="S1" />
            <blockpin signalname="XLXN_1" name="O" />
        </block>
        <block symbolname="vcc" name="XLXI_4">
            <blockpin signalname="XLXN_4" name="P" />
        </block>
        <block symbolname="fdr" name="XLXI_5">
            <blockpin signalname="CLK" name="C" />
            <blockpin signalname="XLXN_1" name="D" />
            <blockpin signalname="CLEARC" name="R" />
            <blockpin signalname="Qi" name="Q" />
        </block>
        <block symbolname="inv" name="XLXI_6">
            <blockpin signalname="Qi" name="I" />
            <blockpin signalname="XLXN_9" name="O" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="XLXN_1">
            <wire x2="2048" y1="1008" y2="1008" x1="1600" />
        </branch>
        <branch name="Qi">
            <wire x2="848" y1="800" y2="912" x1="848" />
            <wire x2="1280" y1="912" y2="912" x1="848" />
            <wire x2="848" y1="912" y2="1040" x1="848" />
            <wire x2="928" y1="1040" y2="1040" x1="848" />
            <wire x2="2672" y1="800" y2="800" x1="848" />
            <wire x2="2672" y1="800" y2="1008" x1="2672" />
            <wire x2="2928" y1="1008" y2="1008" x1="2672" />
            <wire x2="2672" y1="1008" y2="1008" x1="2432" />
        </branch>
        <branch name="CLK">
            <wire x2="1664" y1="1840" y2="1840" x1="960" />
            <wire x2="1664" y1="1136" y2="1840" x1="1664" />
            <wire x2="2048" y1="1136" y2="1136" x1="1664" />
        </branch>
        <branch name="XLXN_4">
            <wire x2="1280" y1="1296" y2="1296" x1="1216" />
        </branch>
        <branch name="CPLC">
            <wire x2="1264" y1="1232" y2="1232" x1="704" />
            <wire x2="1280" y1="1232" y2="1232" x1="1264" />
        </branch>
        <branch name="LOADC">
            <wire x2="1264" y1="1168" y2="1168" x1="720" />
            <wire x2="1280" y1="1168" y2="1168" x1="1264" />
        </branch>
        <branch name="Di">
            <wire x2="1280" y1="976" y2="976" x1="656" />
        </branch>
        <branch name="XLXN_9">
            <wire x2="1248" y1="1040" y2="1040" x1="1152" />
            <wire x2="1280" y1="1040" y2="1040" x1="1248" />
        </branch>
        <instance x="1280" y="1328" name="XLXI_2" orien="R0" />
        <instance x="1216" y="1360" name="XLXI_4" orien="R270" />
        <iomarker fontsize="28" x="960" y="1840" name="CLK" orien="R180" />
        <iomarker fontsize="28" x="2928" y="1008" name="Qi" orien="R0" />
        <iomarker fontsize="28" x="656" y="976" name="Di" orien="R180" />
        <instance x="2048" y="1264" name="XLXI_5" orien="R0" />
        <iomarker fontsize="28" x="704" y="1232" name="CPLC" orien="R180" />
        <iomarker fontsize="28" x="720" y="1168" name="LOADC" orien="R180" />
        <instance x="928" y="1072" name="XLXI_6" orien="R0" />
        <branch name="CLEARC">
            <wire x2="2032" y1="1232" y2="1232" x1="1952" />
            <wire x2="2048" y1="1232" y2="1232" x1="2032" />
        </branch>
        <iomarker fontsize="28" x="1952" y="1232" name="CLEARC" orien="R180" />
    </sheet>
</drawing>