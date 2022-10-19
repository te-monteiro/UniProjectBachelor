<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="XLXN_1(7:0)" />
        <signal name="CLK" />
        <signal name="LOADTEMP" />
        <signal name="CLEARTEMP" />
        <signal name="ZERO" />
        <signal name="XLXN_43" />
        <signal name="XLXN_42" />
        <signal name="RES(7:0)" />
        <signal name="DATA(7:0)" />
        <signal name="ANL" />
        <signal name="ADD" />
        <signal name="DEC" />
        <signal name="PASSTHROUGH" />
        <signal name="LOADC" />
        <signal name="CLEARC" />
        <signal name="CPLC" />
        <signal name="SEL" />
        <signal name="OPER1(7:0)" />
        <signal name="OPER2(7:0)" />
        <port polarity="Input" name="CLK" />
        <port polarity="Input" name="LOADTEMP" />
        <port polarity="Input" name="CLEARTEMP" />
        <port polarity="Output" name="ZERO" />
        <port polarity="Output" name="RES(7:0)" />
        <port polarity="Input" name="ANL" />
        <port polarity="Input" name="ADD" />
        <port polarity="Input" name="DEC" />
        <port polarity="Input" name="PASSTHROUGH" />
        <port polarity="Input" name="LOADC" />
        <port polarity="Input" name="CLEARC" />
        <port polarity="Input" name="CPLC" />
        <port polarity="Input" name="SEL" />
        <port polarity="Input" name="OPER1(7:0)" />
        <port polarity="Input" name="OPER2(7:0)" />
        <blockdef name="ULA">
            <timestamp>2016-11-19T23:21:26</timestamp>
            <rect width="384" x="80" y="-464" height="384" />
            <line x2="544" y1="-432" y2="-432" x1="464" />
            <line x2="544" y1="-352" y2="-352" x1="464" />
            <line x2="544" y1="-272" y2="-272" x1="464" />
            <line x2="544" y1="-192" y2="-192" x1="464" />
            <line x2="544" y1="-112" y2="-112" x1="464" />
            <line x2="272" y1="-80" y2="0" x1="272" />
            <rect width="24" x="260" y="-80" height="80" />
            <line x2="320" y1="-464" y2="-544" x1="320" />
            <rect width="24" x="308" y="-544" height="80" />
            <line x2="208" y1="-464" y2="-544" x1="208" />
            <rect width="24" x="196" y="-544" height="80" />
            <line x2="0" y1="-112" y2="-112" x1="80" />
        </blockdef>
        <blockdef name="TEMP">
            <timestamp>2016-11-20T0:7:24</timestamp>
            <rect width="256" x="64" y="-320" height="256" />
            <line x2="192" y1="-64" y2="0" x1="192" />
            <rect width="24" x="180" y="-64" height="64" />
            <line x2="0" y1="-192" y2="-192" x1="64" />
            <line x2="384" y1="-224" y2="-224" x1="320" />
            <line x2="384" y1="-160" y2="-160" x1="320" />
            <line x2="192" y1="-320" y2="-384" x1="192" />
            <rect width="24" x="180" y="-384" height="64" />
        </blockdef>
        <blockdef name="Z">
            <timestamp>2016-11-20T0:14:45</timestamp>
            <rect width="256" x="64" y="-64" height="64" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
            <rect width="64" x="0" y="-44" height="24" />
            <line x2="384" y1="-32" y2="-32" x1="320" />
        </blockdef>
        <blockdef name="C">
            <timestamp>2016-11-20T0:20:58</timestamp>
            <rect width="256" x="64" y="-320" height="256" />
            <line x2="384" y1="-192" y2="-192" x1="320" />
            <line x2="192" y1="-320" y2="-384" x1="192" />
            <line x2="0" y1="-192" y2="-192" x1="64" />
            <line x2="192" y1="-64" y2="0" x1="192" />
            <line x2="384" y1="-128" y2="-128" x1="320" />
            <line x2="384" y1="-256" y2="-256" x1="320" />
        </blockdef>
        <blockdef name="m2_1_8bit">
            <timestamp>2016-11-20T9:19:21</timestamp>
            <rect width="256" x="64" y="-192" height="192" />
            <line x2="0" y1="-160" y2="-160" x1="64" />
            <rect width="64" x="0" y="-172" height="24" />
            <line x2="0" y1="-96" y2="-96" x1="64" />
            <rect width="64" x="0" y="-108" height="24" />
            <line x2="384" y1="-160" y2="-160" x1="320" />
            <rect width="64" x="320" y="-172" height="24" />
            <line x2="0" y1="-32" y2="-32" x1="64" />
        </blockdef>
        <block symbolname="TEMP" name="XLXI_2">
            <blockpin signalname="RES(7:0)" name="Q(7:0)" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="LOADTEMP" name="LOAD" />
            <blockpin signalname="CLEARTEMP" name="CLEAR" />
            <blockpin signalname="XLXN_1(7:0)" name="D(7:0)" />
        </block>
        <block symbolname="Z" name="XLXI_3">
            <blockpin signalname="RES(7:0)" name="IN_Z(7:0)" />
            <blockpin signalname="ZERO" name="OUT_Z" />
        </block>
        <block symbolname="ULA" name="XLXI_1">
            <blockpin signalname="PASSTHROUGH" name="PASS" />
            <blockpin signalname="DEC" name="DEC" />
            <blockpin signalname="ADD" name="ADD" />
            <blockpin signalname="ANL" name="ANL" />
            <blockpin signalname="XLXN_42" name="Co" />
            <blockpin signalname="XLXN_1(7:0)" name="ULAOUT(7:0)" />
            <blockpin signalname="DATA(7:0)" name="ULAIN2(7:0)" />
            <blockpin signalname="RES(7:0)" name="ULAIN1(7:0)" />
            <blockpin signalname="XLXN_43" name="Ci" />
        </block>
        <block symbolname="C" name="XLXI_4">
            <blockpin signalname="CLEARC" name="CLEARC" />
            <blockpin signalname="XLXN_42" name="Di" />
            <blockpin signalname="CLK" name="CLK" />
            <blockpin signalname="XLXN_43" name="Qi" />
            <blockpin signalname="CPLC" name="CPLC" />
            <blockpin signalname="LOADC" name="LOADC" />
        </block>
        <block symbolname="m2_1_8bit" name="XLXI_5">
            <blockpin signalname="OPER1(7:0)" name="D0(7:0)" />
            <blockpin signalname="OPER2(7:0)" name="D1(7:0)" />
            <blockpin signalname="DATA(7:0)" name="O(7:0)" />
            <blockpin signalname="SEL" name="S0" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="XLXN_1(7:0)">
            <wire x2="1696" y1="1456" y2="1808" x1="1696" />
        </branch>
        <instance x="1504" y="2192" name="XLXI_2" orien="R0">
        </instance>
        <branch name="CLK">
            <wire x2="944" y1="2000" y2="2000" x1="656" />
            <wire x2="1488" y1="2000" y2="2000" x1="944" />
            <wire x2="1504" y1="2000" y2="2000" x1="1488" />
            <wire x2="2112" y1="1568" y2="1568" x1="944" />
            <wire x2="944" y1="1568" y2="2000" x1="944" />
        </branch>
        <branch name="LOADTEMP">
            <wire x2="1904" y1="1968" y2="1968" x1="1888" />
            <wire x2="2720" y1="1968" y2="1968" x1="1904" />
        </branch>
        <branch name="CLEARTEMP">
            <wire x2="1904" y1="2032" y2="2032" x1="1888" />
            <wire x2="2704" y1="2032" y2="2032" x1="1904" />
        </branch>
        <instance x="1904" y="2320" name="XLXI_3" orien="R0">
        </instance>
        <branch name="ZERO">
            <wire x2="2384" y1="2288" y2="2288" x1="2288" />
            <wire x2="2384" y1="2288" y2="2528" x1="2384" />
        </branch>
        <branch name="XLXN_43">
            <wire x2="1424" y1="1344" y2="1344" x1="1408" />
            <wire x2="1408" y1="1344" y2="1776" x1="1408" />
            <wire x2="2304" y1="1776" y2="1776" x1="1408" />
            <wire x2="2304" y1="1760" y2="1776" x1="2304" />
        </branch>
        <branch name="XLXN_42">
            <wire x2="2304" y1="1344" y2="1344" x1="1968" />
            <wire x2="2304" y1="1344" y2="1376" x1="2304" />
        </branch>
        <branch name="RES(7:0)">
            <wire x2="1280" y1="848" y2="2288" x1="1280" />
            <wire x2="1696" y1="2288" y2="2288" x1="1280" />
            <wire x2="1904" y1="2288" y2="2288" x1="1696" />
            <wire x2="1696" y1="2288" y2="2496" x1="1696" />
            <wire x2="1632" y1="848" y2="848" x1="1280" />
            <wire x2="1632" y1="848" y2="912" x1="1632" />
            <wire x2="1696" y1="2192" y2="2288" x1="1696" />
        </branch>
        <branch name="DATA(7:0)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1744" y="816" type="branch" />
            <wire x2="1744" y1="720" y2="816" x1="1744" />
            <wire x2="1744" y1="816" y2="912" x1="1744" />
        </branch>
        <branch name="ANL">
            <wire x2="1984" y1="1264" y2="1264" x1="1968" />
            <wire x2="2816" y1="1264" y2="1264" x1="1984" />
        </branch>
        <branch name="ADD">
            <wire x2="1984" y1="1184" y2="1184" x1="1968" />
            <wire x2="2816" y1="1184" y2="1184" x1="1984" />
        </branch>
        <branch name="DEC">
            <wire x2="1984" y1="1104" y2="1104" x1="1968" />
            <wire x2="2816" y1="1104" y2="1104" x1="1984" />
        </branch>
        <branch name="PASSTHROUGH">
            <wire x2="1984" y1="1024" y2="1024" x1="1968" />
            <wire x2="2656" y1="1024" y2="1024" x1="1984" />
        </branch>
        <instance x="1424" y="1456" name="XLXI_1" orien="R0">
        </instance>
        <instance x="2112" y="1760" name="XLXI_4" orien="R0">
        </instance>
        <branch name="LOADC">
            <wire x2="2512" y1="1504" y2="1504" x1="2496" />
            <wire x2="2784" y1="1504" y2="1504" x1="2512" />
        </branch>
        <branch name="CLEARC">
            <wire x2="2512" y1="1568" y2="1568" x1="2496" />
            <wire x2="2768" y1="1568" y2="1568" x1="2512" />
        </branch>
        <branch name="CPLC">
            <wire x2="2512" y1="1632" y2="1632" x1="2496" />
            <wire x2="2800" y1="1632" y2="1632" x1="2512" />
        </branch>
        <iomarker fontsize="28" x="1696" y="2496" name="RES(7:0)" orien="R90" />
        <iomarker fontsize="28" x="2384" y="2528" name="ZERO" orien="R90" />
        <iomarker fontsize="28" x="2656" y="1024" name="PASSTHROUGH" orien="R0" />
        <iomarker fontsize="28" x="2816" y="1104" name="DEC" orien="R0" />
        <iomarker fontsize="28" x="2816" y="1184" name="ADD" orien="R0" />
        <iomarker fontsize="28" x="656" y="2000" name="CLK" orien="R180" />
        <iomarker fontsize="28" x="2784" y="1504" name="LOADC" orien="R0" />
        <iomarker fontsize="28" x="2768" y="1568" name="CLEARC" orien="R0" />
        <iomarker fontsize="28" x="2720" y="1968" name="LOADTEMP" orien="R0" />
        <iomarker fontsize="28" x="2704" y="2032" name="CLEARTEMP" orien="R0" />
        <iomarker fontsize="28" x="2800" y="1632" name="CPLC" orien="R0" />
        <iomarker fontsize="28" x="2816" y="1264" name="ANL" orien="R0" />
        <branch name="SEL">
            <wire x2="1872" y1="240" y2="336" x1="1872" />
            <wire x2="2800" y1="240" y2="240" x1="1872" />
        </branch>
        <branch name="OPER1(7:0)">
            <wire x2="1744" y1="240" y2="336" x1="1744" />
        </branch>
        <branch name="OPER2(7:0)">
            <wire x2="1808" y1="240" y2="336" x1="1808" />
        </branch>
        <iomarker fontsize="28" x="2800" y="240" name="SEL" orien="R0" />
        <iomarker fontsize="28" x="1744" y="240" name="OPER1(7:0)" orien="R270" />
        <iomarker fontsize="28" x="1808" y="240" name="OPER2(7:0)" orien="R270" />
        <instance x="1904" y="336" name="XLXI_5" orien="M90">
        </instance>
    </sheet>
</drawing>