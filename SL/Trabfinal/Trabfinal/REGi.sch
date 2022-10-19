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
        <signal name="CLEAR" />
        <signal name="LOAD" />
        <signal name="XLXN_7" />
        <signal name="Di" />
        <signal name="XLXN_9" />
        <port polarity="Output" name="Qi" />
        <port polarity="Input" name="CLK" />
        <port polarity="Input" name="CLEAR" />
        <port polarity="Input" name="LOAD" />
        <port polarity="Input" name="Di" />
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
        <blockdef name="fd">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <rect width="256" x="64" y="-320" height="256" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-256" y2="-256" x1="0" />
            <line x2="320" y1="-256" y2="-256" x1="384" />
            <line x2="64" y1="-128" y2="-144" x1="80" />
            <line x2="80" y1="-112" y2="-128" x1="64" />
        </blockdef>
        <blockdef name="vcc">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-64" x1="64" />
            <line x2="64" y1="0" y2="-32" x1="64" />
            <line x2="32" y1="-64" y2="-64" x1="96" />
        </blockdef>
        <blockdef name="gnd">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-96" x1="64" />
            <line x2="52" y1="-48" y2="-48" x1="76" />
            <line x2="60" y1="-32" y2="-32" x1="68" />
            <line x2="40" y1="-64" y2="-64" x1="88" />
            <line x2="64" y1="-64" y2="-80" x1="64" />
            <line x2="64" y1="-128" y2="-96" x1="64" />
        </blockdef>
        <block symbolname="m4_1e" name="XLXI_1">
            <blockpin signalname="Qi" name="D0" />
            <blockpin signalname="Di" name="D1" />
            <blockpin signalname="XLXN_9" name="D2" />
            <blockpin name="D3" />
            <blockpin signalname="XLXN_4" name="E" />
            <blockpin signalname="LOAD" name="S0" />
            <blockpin signalname="CLEAR" name="S1" />
            <blockpin signalname="XLXN_1" name="O" />
        </block>
        <block symbolname="fd" name="XLXI_2">
            <blockpin signalname="CLK" name="C" />
            <blockpin signalname="XLXN_1" name="D" />
            <blockpin signalname="Qi" name="Q" />
        </block>
        <block symbolname="vcc" name="XLXI_3">
            <blockpin signalname="XLXN_4" name="P" />
        </block>
        <block symbolname="gnd" name="XLXI_4">
            <blockpin signalname="XLXN_9" name="G" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <instance x="2016" y="1424" name="XLXI_2" orien="R0" />
        <branch name="XLXN_1">
            <wire x2="2016" y1="1168" y2="1168" x1="1568" />
        </branch>
        <instance x="1248" y="1488" name="XLXI_1" orien="R0" />
        <branch name="Qi">
            <wire x2="1104" y1="960" y2="1072" x1="1104" />
            <wire x2="1248" y1="1072" y2="1072" x1="1104" />
            <wire x2="2640" y1="960" y2="960" x1="1104" />
            <wire x2="2640" y1="960" y2="1168" x1="2640" />
            <wire x2="2896" y1="1168" y2="1168" x1="2640" />
            <wire x2="2640" y1="1168" y2="1168" x1="2400" />
        </branch>
        <branch name="CLK">
            <wire x2="1632" y1="2000" y2="2000" x1="928" />
            <wire x2="1632" y1="1296" y2="2000" x1="1632" />
            <wire x2="2016" y1="1296" y2="1296" x1="1632" />
        </branch>
        <iomarker fontsize="28" x="928" y="2000" name="CLK" orien="R180" />
        <branch name="XLXN_4">
            <wire x2="1248" y1="1456" y2="1456" x1="1184" />
        </branch>
        <branch name="CLEAR">
            <wire x2="1232" y1="1392" y2="1392" x1="688" />
            <wire x2="1248" y1="1392" y2="1392" x1="1232" />
        </branch>
        <branch name="LOAD">
            <wire x2="1232" y1="1328" y2="1328" x1="672" />
            <wire x2="1248" y1="1328" y2="1328" x1="1232" />
        </branch>
        <iomarker fontsize="28" x="672" y="1328" name="LOAD" orien="R180" />
        <iomarker fontsize="28" x="688" y="1392" name="CLEAR" orien="R180" />
        <iomarker fontsize="28" x="2896" y="1168" name="Qi" orien="R0" />
        <branch name="Di">
            <wire x2="1232" y1="1136" y2="1136" x1="624" />
            <wire x2="1248" y1="1136" y2="1136" x1="1232" />
        </branch>
        <iomarker fontsize="28" x="624" y="1136" name="Di" orien="R180" />
        <branch name="XLXN_9">
            <wire x2="1248" y1="1200" y2="1200" x1="1216" />
        </branch>
        <instance x="1088" y="1136" name="XLXI_4" orien="R90" />
        <instance x="1184" y="1520" name="XLXI_3" orien="R270" />
    </sheet>
</drawing>