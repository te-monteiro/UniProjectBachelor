<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="S_1(7:0)" />
        <signal name="S_0(7:0)" />
        <signal name="S_1(7)" />
        <signal name="S_1(6)" />
        <signal name="S_1(5)" />
        <signal name="S_1(4)" />
        <signal name="S_1(3)" />
        <signal name="S_1(2)" />
        <signal name="S_1(1)" />
        <signal name="S_1(0)" />
        <signal name="S1" />
        <signal name="S0" />
        <signal name="S_0(7)" />
        <signal name="S_0(6)" />
        <signal name="S_0(5)" />
        <signal name="S_0(4)" />
        <signal name="S_0(3)" />
        <signal name="S_0(2)" />
        <signal name="S_0(0)" />
        <signal name="S_0(1)" />
        <signal name="D0(7:0)" />
        <signal name="D1(7:0)" />
        <signal name="D2(7:0)" />
        <signal name="D3(7:0)" />
        <signal name="O(7:0)" />
        <signal name="XLXN_22(7:0)" />
        <port polarity="Input" name="S1" />
        <port polarity="Input" name="S0" />
        <port polarity="Input" name="D0(7:0)" />
        <port polarity="Input" name="D1(7:0)" />
        <port polarity="Input" name="D2(7:0)" />
        <port polarity="Input" name="D3(7:0)" />
        <port polarity="Output" name="O(7:0)" />
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
        <blockdef name="buf">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-32" x1="0" />
            <line x2="128" y1="-32" y2="-32" x1="224" />
            <line x2="128" y1="0" y2="-32" x1="64" />
            <line x2="64" y1="-32" y2="-64" x1="128" />
            <line x2="64" y1="-64" y2="0" x1="64" />
        </blockdef>
        <blockdef name="vcc">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-64" x1="64" />
            <line x2="64" y1="0" y2="-32" x1="64" />
            <line x2="32" y1="-64" y2="-64" x1="96" />
        </blockdef>
        <block symbolname="buf" name="XLXI_48">
            <blockpin signalname="S1" name="I" />
            <blockpin signalname="S_1(7)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_49">
            <blockpin signalname="S1" name="I" />
            <blockpin signalname="S_1(6)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_50">
            <blockpin signalname="S1" name="I" />
            <blockpin signalname="S_1(5)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_51">
            <blockpin signalname="S1" name="I" />
            <blockpin signalname="S_1(4)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_52">
            <blockpin signalname="S1" name="I" />
            <blockpin signalname="S_1(3)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_53">
            <blockpin signalname="S1" name="I" />
            <blockpin signalname="S_1(2)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_54">
            <blockpin signalname="S1" name="I" />
            <blockpin signalname="S_1(1)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_55">
            <blockpin signalname="S1" name="I" />
            <blockpin signalname="S_1(0)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_88">
            <blockpin signalname="S0" name="I" />
            <blockpin signalname="S_0(7)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_89">
            <blockpin signalname="S0" name="I" />
            <blockpin signalname="S_0(6)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_90">
            <blockpin signalname="S0" name="I" />
            <blockpin signalname="S_0(5)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_91">
            <blockpin signalname="S0" name="I" />
            <blockpin signalname="S_0(4)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_92">
            <blockpin signalname="S0" name="I" />
            <blockpin signalname="S_0(3)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_93">
            <blockpin signalname="S0" name="I" />
            <blockpin signalname="S_0(2)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_94">
            <blockpin signalname="S0" name="I" />
            <blockpin signalname="S_0(1)" name="O" />
        </block>
        <block symbolname="buf" name="XLXI_95">
            <blockpin signalname="S0" name="I" />
            <blockpin signalname="S_0(0)" name="O" />
        </block>
        <block symbolname="m4_1e" name="XLXI_1(7:0)">
            <blockpin signalname="D0(7:0)" name="D0" />
            <blockpin signalname="D1(7:0)" name="D1" />
            <blockpin signalname="D2(7:0)" name="D2" />
            <blockpin signalname="D3(7:0)" name="D3" />
            <blockpin signalname="XLXN_22(7:0)" name="E" />
            <blockpin signalname="S_0(7:0)" name="S0" />
            <blockpin signalname="S_1(7:0)" name="S1" />
            <blockpin signalname="O(7:0)" name="O" />
        </block>
        <block symbolname="vcc" name="XLXI_96(7:0)">
            <blockpin signalname="XLXN_22(7:0)" name="P" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="S_1(7:0)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2320" y="1088" type="branch" />
            <wire x2="1248" y1="736" y2="1088" x1="1248" />
            <wire x2="1712" y1="1088" y2="1088" x1="1248" />
            <wire x2="1776" y1="1088" y2="1088" x1="1712" />
            <wire x2="1840" y1="1088" y2="1088" x1="1776" />
            <wire x2="1904" y1="1088" y2="1088" x1="1840" />
            <wire x2="1968" y1="1088" y2="1088" x1="1904" />
            <wire x2="2032" y1="1088" y2="1088" x1="1968" />
            <wire x2="2096" y1="1088" y2="1088" x1="2032" />
            <wire x2="2160" y1="1088" y2="1088" x1="2096" />
            <wire x2="2320" y1="1088" y2="1088" x1="2160" />
            <wire x2="2400" y1="1088" y2="1088" x1="2320" />
            <wire x2="1600" y1="736" y2="736" x1="1248" />
        </branch>
        <branch name="S_0(7:0)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2192" y="1792" type="branch" />
            <wire x2="928" y1="672" y2="1792" x1="928" />
            <wire x2="1408" y1="1792" y2="1792" x1="928" />
            <wire x2="1472" y1="1792" y2="1792" x1="1408" />
            <wire x2="1536" y1="1792" y2="1792" x1="1472" />
            <wire x2="1600" y1="1792" y2="1792" x1="1536" />
            <wire x2="1664" y1="1792" y2="1792" x1="1600" />
            <wire x2="1728" y1="1792" y2="1792" x1="1664" />
            <wire x2="1792" y1="1792" y2="1792" x1="1728" />
            <wire x2="1856" y1="1792" y2="1792" x1="1792" />
            <wire x2="2192" y1="1792" y2="1792" x1="1856" />
            <wire x2="2272" y1="1792" y2="1792" x1="2192" />
            <wire x2="1600" y1="672" y2="672" x1="928" />
        </branch>
        <bustap x2="1712" y1="1088" y2="1184" x1="1712" />
        <bustap x2="1776" y1="1088" y2="1184" x1="1776" />
        <bustap x2="1840" y1="1088" y2="1184" x1="1840" />
        <bustap x2="1904" y1="1088" y2="1184" x1="1904" />
        <bustap x2="1968" y1="1088" y2="1184" x1="1968" />
        <bustap x2="2032" y1="1088" y2="1184" x1="2032" />
        <bustap x2="2096" y1="1088" y2="1184" x1="2096" />
        <bustap x2="2160" y1="1088" y2="1184" x1="2160" />
        <branch name="S_1(7)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1712" y="1248" type="branch" />
            <wire x2="1712" y1="1184" y2="1248" x1="1712" />
            <wire x2="1712" y1="1248" y2="1312" x1="1712" />
        </branch>
        <branch name="S_1(6)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1776" y="1248" type="branch" />
            <wire x2="1776" y1="1184" y2="1248" x1="1776" />
            <wire x2="1776" y1="1248" y2="1312" x1="1776" />
        </branch>
        <branch name="S_1(5)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1840" y="1248" type="branch" />
            <wire x2="1840" y1="1184" y2="1248" x1="1840" />
            <wire x2="1840" y1="1248" y2="1312" x1="1840" />
        </branch>
        <branch name="S_1(4)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1904" y="1248" type="branch" />
            <wire x2="1904" y1="1184" y2="1248" x1="1904" />
            <wire x2="1904" y1="1248" y2="1312" x1="1904" />
        </branch>
        <branch name="S_1(3)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1968" y="1248" type="branch" />
            <wire x2="1968" y1="1184" y2="1248" x1="1968" />
            <wire x2="1968" y1="1248" y2="1312" x1="1968" />
        </branch>
        <branch name="S_1(2)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2032" y="1248" type="branch" />
            <wire x2="2032" y1="1184" y2="1248" x1="2032" />
            <wire x2="2032" y1="1248" y2="1312" x1="2032" />
        </branch>
        <branch name="S_1(1)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2096" y="1248" type="branch" />
            <wire x2="2096" y1="1184" y2="1248" x1="2096" />
            <wire x2="2096" y1="1248" y2="1312" x1="2096" />
        </branch>
        <branch name="S_1(0)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2160" y="1248" type="branch" />
            <wire x2="2160" y1="1184" y2="1248" x1="2160" />
            <wire x2="2160" y1="1248" y2="1312" x1="2160" />
        </branch>
        <instance x="1744" y="1536" name="XLXI_48" orien="R270" />
        <instance x="1808" y="1536" name="XLXI_49" orien="R270" />
        <instance x="1872" y="1536" name="XLXI_50" orien="R270" />
        <instance x="1936" y="1536" name="XLXI_51" orien="R270" />
        <instance x="2000" y="1536" name="XLXI_52" orien="R270" />
        <instance x="2064" y="1536" name="XLXI_53" orien="R270" />
        <instance x="2128" y="1536" name="XLXI_54" orien="R270" />
        <instance x="2192" y="1536" name="XLXI_55" orien="R270" />
        <branch name="S1">
            <wire x2="1712" y1="1536" y2="1616" x1="1712" />
            <wire x2="1776" y1="1616" y2="1616" x1="1712" />
            <wire x2="1840" y1="1616" y2="1616" x1="1776" />
            <wire x2="1904" y1="1616" y2="1616" x1="1840" />
            <wire x2="1968" y1="1616" y2="1616" x1="1904" />
            <wire x2="2032" y1="1616" y2="1616" x1="1968" />
            <wire x2="2096" y1="1616" y2="1616" x1="2032" />
            <wire x2="2160" y1="1616" y2="1616" x1="2096" />
            <wire x2="2704" y1="1616" y2="1616" x1="2160" />
            <wire x2="1776" y1="1536" y2="1616" x1="1776" />
            <wire x2="1840" y1="1536" y2="1616" x1="1840" />
            <wire x2="1904" y1="1536" y2="1616" x1="1904" />
            <wire x2="1968" y1="1536" y2="1616" x1="1968" />
            <wire x2="2032" y1="1536" y2="1616" x1="2032" />
            <wire x2="2096" y1="1536" y2="1616" x1="2096" />
            <wire x2="2160" y1="1536" y2="1616" x1="2160" />
        </branch>
        <instance x="1440" y="2288" name="XLXI_88" orien="R270" />
        <instance x="1504" y="2288" name="XLXI_89" orien="R270" />
        <instance x="1568" y="2288" name="XLXI_90" orien="R270" />
        <instance x="1632" y="2288" name="XLXI_91" orien="R270" />
        <instance x="1696" y="2288" name="XLXI_92" orien="R270" />
        <instance x="1760" y="2288" name="XLXI_93" orien="R270" />
        <instance x="1824" y="2288" name="XLXI_94" orien="R270" />
        <instance x="1888" y="2288" name="XLXI_95" orien="R270" />
        <branch name="S0">
            <wire x2="1408" y1="2288" y2="2368" x1="1408" />
            <wire x2="1472" y1="2368" y2="2368" x1="1408" />
            <wire x2="1536" y1="2368" y2="2368" x1="1472" />
            <wire x2="1600" y1="2368" y2="2368" x1="1536" />
            <wire x2="1664" y1="2368" y2="2368" x1="1600" />
            <wire x2="1728" y1="2368" y2="2368" x1="1664" />
            <wire x2="1792" y1="2368" y2="2368" x1="1728" />
            <wire x2="1856" y1="2368" y2="2368" x1="1792" />
            <wire x2="2720" y1="2368" y2="2368" x1="1856" />
            <wire x2="1472" y1="2288" y2="2368" x1="1472" />
            <wire x2="1536" y1="2288" y2="2368" x1="1536" />
            <wire x2="1600" y1="2288" y2="2368" x1="1600" />
            <wire x2="1664" y1="2288" y2="2368" x1="1664" />
            <wire x2="1728" y1="2288" y2="2368" x1="1728" />
            <wire x2="1792" y1="2288" y2="2368" x1="1792" />
            <wire x2="1856" y1="2288" y2="2368" x1="1856" />
        </branch>
        <bustap x2="1408" y1="1792" y2="1888" x1="1408" />
        <branch name="S_0(7)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1408" y="1968" type="branch" />
            <wire x2="1408" y1="1888" y2="1968" x1="1408" />
            <wire x2="1408" y1="1968" y2="2064" x1="1408" />
        </branch>
        <bustap x2="1472" y1="1792" y2="1888" x1="1472" />
        <branch name="S_0(6)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1472" y="1968" type="branch" />
            <wire x2="1472" y1="1888" y2="1968" x1="1472" />
            <wire x2="1472" y1="1968" y2="2064" x1="1472" />
        </branch>
        <bustap x2="1536" y1="1792" y2="1888" x1="1536" />
        <branch name="S_0(5)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1536" y="1968" type="branch" />
            <wire x2="1536" y1="1888" y2="1968" x1="1536" />
            <wire x2="1536" y1="1968" y2="2064" x1="1536" />
        </branch>
        <bustap x2="1600" y1="1792" y2="1888" x1="1600" />
        <branch name="S_0(4)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1600" y="1968" type="branch" />
            <wire x2="1600" y1="1888" y2="1968" x1="1600" />
            <wire x2="1600" y1="1968" y2="2064" x1="1600" />
        </branch>
        <bustap x2="1664" y1="1792" y2="1888" x1="1664" />
        <branch name="S_0(3)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1664" y="1968" type="branch" />
            <wire x2="1664" y1="1888" y2="1968" x1="1664" />
            <wire x2="1664" y1="1968" y2="2064" x1="1664" />
        </branch>
        <bustap x2="1728" y1="1792" y2="1888" x1="1728" />
        <branch name="S_0(2)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1728" y="1968" type="branch" />
            <wire x2="1728" y1="1888" y2="1968" x1="1728" />
            <wire x2="1728" y1="1968" y2="2064" x1="1728" />
        </branch>
        <bustap x2="1856" y1="1792" y2="1888" x1="1856" />
        <branch name="S_0(0)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1856" y="1968" type="branch" />
            <wire x2="1856" y1="1888" y2="1968" x1="1856" />
            <wire x2="1856" y1="1968" y2="2064" x1="1856" />
        </branch>
        <bustap x2="1792" y1="1792" y2="1888" x1="1792" />
        <branch name="S_0(1)">
            <attrtext style="alignment:SOFT-TVCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1792" y="1968" type="branch" />
            <wire x2="1792" y1="1888" y2="1968" x1="1792" />
            <wire x2="1792" y1="1968" y2="2064" x1="1792" />
        </branch>
        <instance x="1600" y="832" name="XLXI_1(7:0)" orien="R0" />
        <branch name="D0(7:0)">
            <wire x2="1600" y1="416" y2="416" x1="736" />
        </branch>
        <branch name="D1(7:0)">
            <wire x2="1600" y1="480" y2="480" x1="736" />
        </branch>
        <branch name="D2(7:0)">
            <wire x2="1600" y1="544" y2="544" x1="736" />
        </branch>
        <branch name="D3(7:0)">
            <wire x2="1600" y1="608" y2="608" x1="736" />
        </branch>
        <branch name="O(7:0)">
            <wire x2="2704" y1="512" y2="512" x1="1920" />
        </branch>
        <branch name="XLXN_22(7:0)">
            <wire x2="1600" y1="800" y2="800" x1="1568" />
        </branch>
        <instance x="1568" y="864" name="XLXI_96(7:0)" orien="R270" />
        <iomarker fontsize="28" x="2704" y="1616" name="S1" orien="R0" />
        <iomarker fontsize="28" x="2720" y="2368" name="S0" orien="R0" />
        <iomarker fontsize="28" x="736" y="416" name="D0(7:0)" orien="R180" />
        <iomarker fontsize="28" x="736" y="480" name="D1(7:0)" orien="R180" />
        <iomarker fontsize="28" x="736" y="544" name="D2(7:0)" orien="R180" />
        <iomarker fontsize="28" x="736" y="608" name="D3(7:0)" orien="R180" />
        <iomarker fontsize="28" x="2704" y="512" name="O(7:0)" orien="R0" />
    </sheet>
</drawing>