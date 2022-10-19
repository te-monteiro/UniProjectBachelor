<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="XLXN_7" />
        <signal name="XLXN_19(7:0)" />
        <signal name="XLXN_22" />
        <signal name="XLXN_23" />
        <signal name="XLXN_24(7:0)" />
        <signal name="XLXN_25(7:0)" />
        <signal name="XLXN_26(7:0)" />
        <signal name="XLXN_1" />
        <signal name="XLXN_2" />
        <signal name="PASS" />
        <signal name="DEC" />
        <signal name="ADD" />
        <signal name="ANL" />
        <signal name="ULAOUT(7:0)" />
        <signal name="XLXN_11(7:0)" />
        <signal name="XLXN_12(7:0)" />
        <signal name="XLXN_13(7:0)" />
        <signal name="XLXN_14" />
        <signal name="XLXN_15(7:0)" />
        <signal name="Ci" />
        <signal name="Co" />
        <signal name="ULAIN2(7:0)" />
        <signal name="ULAIN1(7:0)" />
        <signal name="XLXN_27" />
        <port polarity="Input" name="PASS" />
        <port polarity="Input" name="DEC" />
        <port polarity="Input" name="ADD" />
        <port polarity="Input" name="ANL" />
        <port polarity="Output" name="ULAOUT(7:0)" />
        <port polarity="Input" name="Ci" />
        <port polarity="Output" name="Co" />
        <port polarity="Input" name="ULAIN2(7:0)" />
        <port polarity="Input" name="ULAIN1(7:0)" />
        <blockdef name="m4_1_8bit">
            <timestamp>2014-11-16T1:23:39</timestamp>
            <rect width="256" x="64" y="-384" height="384" />
            <line x2="0" y1="-352" y2="-352" x1="64" />
            <rect width="64" x="0" y="-364" height="24" />
            <line x2="0" y1="-288" y2="-288" x1="64" />
            <rect width="64" x="0" y="-300" height="24" />
            <line x2="0" y1="-224" y2="-224" x1="64" />
            <rect width="64" x="0" y="-236" height="24" />
            <line x2="0" y1="-160" y2="-160" x1="64" />
            <rect width="64" x="0" y="-172" height="24" />
            <line x2="384" y1="-352" y2="-352" x1="320" />
            <rect width="64" x="320" y="-364" height="24" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
            <line x2="0" y1="-96" y2="-96" x1="64" />
        </blockdef>
        <blockdef name="e4_2">
            <timestamp>2014-11-16T1:32:17</timestamp>
            <rect width="256" x="64" y="-256" height="256" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
            <line x2="0" y1="-96" y2="-96" x1="64" />
            <line x2="0" y1="-160" y2="-160" x1="64" />
            <line x2="0" y1="-224" y2="-224" x1="64" />
            <line x2="384" y1="-160" y2="-160" x1="320" />
            <line x2="384" y1="-224" y2="-224" x1="320" />
        </blockdef>
        <blockdef name="add8">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="336" y1="-128" y2="-148" x1="336" />
            <line x2="336" y1="-128" y2="-128" x1="384" />
            <line x2="64" y1="-288" y2="-432" x1="64" />
            <line x2="64" y1="-256" y2="-288" x1="128" />
            <line x2="128" y1="-224" y2="-256" x1="64" />
            <line x2="64" y1="-80" y2="-224" x1="64" />
            <line x2="64" y1="-160" y2="-80" x1="384" />
            <line x2="384" y1="-336" y2="-160" x1="384" />
            <line x2="384" y1="-352" y2="-336" x1="384" />
            <line x2="384" y1="-432" y2="-352" x1="64" />
            <line x2="64" y1="-448" y2="-448" x1="128" />
            <line x2="128" y1="-416" y2="-448" x1="128" />
            <line x2="64" y1="-448" y2="-448" x1="0" />
            <rect width="64" x="384" y="-268" height="24" />
            <line x2="384" y1="-256" y2="-256" x1="448" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="64" y1="-320" y2="-320" x1="0" />
            <rect width="64" x="0" y="-332" height="24" />
            <rect width="64" x="0" y="-204" height="24" />
            <line x2="384" y1="-64" y2="-64" x1="240" />
            <line x2="240" y1="-124" y2="-64" x1="240" />
            <line x2="384" y1="-64" y2="-64" x1="448" />
            <line x2="384" y1="-128" y2="-128" x1="448" />
        </blockdef>
        <blockdef name="adsu8">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="384" y1="-128" y2="-128" x1="448" />
            <line x2="384" y1="-64" y2="-64" x1="448" />
            <line x2="384" y1="-64" y2="-64" x1="240" />
            <line x2="240" y1="-124" y2="-64" x1="240" />
            <rect width="64" x="0" y="-204" height="24" />
            <rect width="64" x="0" y="-332" height="24" />
            <line x2="64" y1="-320" y2="-320" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="384" y1="-256" y2="-256" x1="448" />
            <rect width="64" x="384" y="-268" height="24" />
            <line x2="64" y1="-448" y2="-448" x1="0" />
            <line x2="64" y1="-448" y2="-448" x1="128" />
            <line x2="128" y1="-416" y2="-448" x1="128" />
            <line x2="48" y1="-64" y2="-64" x1="128" />
            <line x2="128" y1="-96" y2="-64" x1="128" />
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-288" y2="-432" x1="64" />
            <line x2="64" y1="-256" y2="-288" x1="128" />
            <line x2="128" y1="-224" y2="-256" x1="64" />
            <line x2="64" y1="-80" y2="-224" x1="64" />
            <line x2="64" y1="-160" y2="-80" x1="384" />
            <line x2="384" y1="-336" y2="-160" x1="384" />
            <line x2="384" y1="-352" y2="-336" x1="384" />
            <line x2="384" y1="-432" y2="-352" x1="64" />
            <line x2="336" y1="-128" y2="-148" x1="336" />
            <line x2="336" y1="-128" y2="-128" x1="384" />
        </blockdef>
        <blockdef name="and2">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="192" y1="-96" y2="-96" x1="256" />
            <arc ex="144" ey="-144" sx="144" sy="-48" r="48" cx="144" cy="-96" />
            <line x2="64" y1="-48" y2="-48" x1="144" />
            <line x2="144" y1="-144" y2="-144" x1="64" />
            <line x2="64" y1="-48" y2="-144" x1="64" />
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
        <block symbolname="m4_1_8bit" name="XLXI_1">
            <blockpin signalname="ULAIN2(7:0)" name="D0(7:0)" />
            <blockpin signalname="XLXN_13(7:0)" name="D1(7:0)" />
            <blockpin signalname="XLXN_12(7:0)" name="D2(7:0)" />
            <blockpin signalname="XLXN_11(7:0)" name="D3(7:0)" />
            <blockpin signalname="ULAOUT(7:0)" name="O(7:0)" />
            <blockpin signalname="XLXN_1" name="S0" />
            <blockpin signalname="XLXN_2" name="S1" />
        </block>
        <block symbolname="e4_2" name="XLXI_2">
            <blockpin signalname="PASS" name="D0" />
            <blockpin signalname="DEC" name="D1" />
            <blockpin signalname="ADD" name="D2" />
            <blockpin signalname="ANL" name="D3" />
            <blockpin signalname="XLXN_1" name="Q0" />
            <blockpin signalname="XLXN_2" name="Q1" />
        </block>
        <block symbolname="adsu8" name="XLXI_4">
            <blockpin signalname="ULAIN1(7:0)" name="A(7:0)" />
            <blockpin signalname="XLXN_14" name="ADD" />
            <blockpin signalname="XLXN_15(7:0)" name="B(7:0)" />
            <blockpin signalname="XLXN_27" name="CI" />
            <blockpin name="CO" />
            <blockpin name="OFL" />
            <blockpin signalname="XLXN_13(7:0)" name="S(7:0)" />
        </block>
        <block symbolname="gnd" name="XLXI_6">
            <blockpin signalname="XLXN_14" name="G" />
        </block>
        <block symbolname="gnd" name="XLXI_7(7:0)">
            <blockpin signalname="XLXN_15(7:0)" name="G" />
        </block>
        <block symbolname="add8" name="XLXI_3">
            <blockpin signalname="ULAIN1(7:0)" name="A(7:0)" />
            <blockpin signalname="ULAIN2(7:0)" name="B(7:0)" />
            <blockpin signalname="Ci" name="CI" />
            <blockpin signalname="Co" name="CO" />
            <blockpin name="OFL" />
            <blockpin signalname="XLXN_12(7:0)" name="S(7:0)" />
        </block>
        <block symbolname="and2" name="XLXI_5(7:0)">
            <blockpin signalname="ULAIN1(7:0)" name="I0" />
            <blockpin signalname="ULAIN2(7:0)" name="I1" />
            <blockpin signalname="XLXN_11(7:0)" name="O" />
        </block>
        <block symbolname="gnd" name="XLXI_8">
            <blockpin signalname="XLXN_27" name="G" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <instance x="1856" y="1872" name="XLXI_1" orien="M90">
        </instance>
        <instance x="2912" y="1600" name="XLXI_2" orien="R180">
        </instance>
        <branch name="XLXN_1">
            <wire x2="2528" y1="1760" y2="1760" x1="1824" />
            <wire x2="1824" y1="1760" y2="1872" x1="1824" />
        </branch>
        <branch name="XLXN_2">
            <wire x2="2528" y1="1824" y2="1824" x1="1760" />
            <wire x2="1760" y1="1824" y2="1872" x1="1760" />
        </branch>
        <branch name="PASS">
            <wire x2="2928" y1="1632" y2="1632" x1="2912" />
            <wire x2="3088" y1="1632" y2="1632" x1="2928" />
        </branch>
        <branch name="DEC">
            <wire x2="3104" y1="1696" y2="1696" x1="2912" />
        </branch>
        <branch name="ADD">
            <wire x2="3104" y1="1760" y2="1760" x1="2912" />
        </branch>
        <branch name="ANL">
            <wire x2="3104" y1="1824" y2="1824" x1="2912" />
        </branch>
        <branch name="ULAOUT(7:0)">
            <wire x2="1504" y1="2256" y2="2336" x1="1504" />
        </branch>
        <instance x="1424" y="912" name="XLXI_4" orien="M90" />
        <branch name="XLXN_11(7:0)">
            <wire x2="2288" y1="1680" y2="1680" x1="1696" />
            <wire x2="1696" y1="1680" y2="1872" x1="1696" />
            <wire x2="2288" y1="1168" y2="1680" x1="2288" />
        </branch>
        <branch name="XLXN_12(7:0)">
            <wire x2="1632" y1="1616" y2="1872" x1="1632" />
            <wire x2="1760" y1="1616" y2="1616" x1="1632" />
            <wire x2="1760" y1="1360" y2="1616" x1="1760" />
        </branch>
        <branch name="XLXN_13(7:0)">
            <wire x2="1168" y1="1360" y2="1616" x1="1168" />
            <wire x2="1568" y1="1616" y2="1616" x1="1168" />
            <wire x2="1568" y1="1616" y2="1872" x1="1568" />
        </branch>
        <branch name="XLXN_14">
            <wire x2="1360" y1="880" y2="912" x1="1360" />
        </branch>
        <instance x="1424" y="752" name="XLXI_6" orien="R180" />
        <branch name="XLXN_15(7:0)">
            <wire x2="1232" y1="880" y2="912" x1="1232" />
        </branch>
        <instance x="1296" y="752" name="XLXI_7(7:0)" orien="R180" />
        <branch name="Ci">
            <wire x2="1568" y1="912" y2="912" x1="1536" />
        </branch>
        <branch name="Co">
            <wire x2="1984" y1="1360" y2="1360" x1="1952" />
        </branch>
        <instance x="2016" y="912" name="XLXI_3" orien="M90" />
        <instance x="2192" y="912" name="XLXI_5(7:0)" orien="R90" />
        <branch name="ULAIN2(7:0)">
            <wire x2="816" y1="656" y2="1808" x1="816" />
            <wire x2="1504" y1="1808" y2="1808" x1="816" />
            <wire x2="1504" y1="1808" y2="1872" x1="1504" />
            <wire x2="1824" y1="656" y2="656" x1="816" />
            <wire x2="2320" y1="656" y2="656" x1="1824" />
            <wire x2="2320" y1="656" y2="912" x1="2320" />
            <wire x2="1824" y1="656" y2="912" x1="1824" />
            <wire x2="2320" y1="448" y2="656" x1="2320" />
        </branch>
        <branch name="ULAIN1(7:0)">
            <wire x2="1104" y1="448" y2="544" x1="1104" />
            <wire x2="1104" y1="544" y2="896" x1="1104" />
            <wire x2="1104" y1="896" y2="912" x1="1104" />
            <wire x2="1696" y1="544" y2="544" x1="1104" />
            <wire x2="1696" y1="544" y2="912" x1="1696" />
            <wire x2="2256" y1="544" y2="544" x1="1696" />
            <wire x2="2256" y1="544" y2="912" x1="2256" />
        </branch>
        <branch name="XLXN_27">
            <wire x2="976" y1="880" y2="912" x1="976" />
        </branch>
        <instance x="1040" y="752" name="XLXI_8" orien="R180" />
        <iomarker fontsize="28" x="3104" y="1824" name="ANL" orien="R0" />
        <iomarker fontsize="28" x="3104" y="1760" name="ADD" orien="R0" />
        <iomarker fontsize="28" x="3104" y="1696" name="DEC" orien="R0" />
        <iomarker fontsize="28" x="1504" y="2336" name="ULAOUT(7:0)" orien="R90" />
        <iomarker fontsize="28" x="1984" y="1360" name="Co" orien="R0" />
        <iomarker fontsize="28" x="1536" y="912" name="Ci" orien="R180" />
        <iomarker fontsize="28" x="2320" y="448" name="ULAIN2(7:0)" orien="R270" />
        <iomarker fontsize="28" x="1104" y="448" name="ULAIN1(7:0)" orien="R270" />
        <iomarker fontsize="28" x="3088" y="1632" name="PASS" orien="R0" />
    </sheet>
</drawing>