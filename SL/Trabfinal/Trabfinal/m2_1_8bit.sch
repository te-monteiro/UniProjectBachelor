<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="S_0(7:0)" />
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
        <signal name="O(7:0)" />
        <port polarity="Input" name="S0" />
        <port polarity="Input" name="D0(7:0)" />
        <port polarity="Input" name="D1(7:0)" />
        <port polarity="Output" name="O(7:0)" />
        <blockdef name="buf">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-32" x1="0" />
            <line x2="128" y1="-32" y2="-32" x1="224" />
            <line x2="128" y1="0" y2="-32" x1="64" />
            <line x2="64" y1="-32" y2="-64" x1="128" />
            <line x2="64" y1="-64" y2="0" x1="64" />
        </blockdef>
        <blockdef name="m2_1">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="96" y1="-64" y2="-192" x1="96" />
            <line x2="96" y1="-96" y2="-64" x1="256" />
            <line x2="256" y1="-160" y2="-96" x1="256" />
            <line x2="256" y1="-192" y2="-160" x1="96" />
            <line x2="96" y1="-32" y2="-32" x1="176" />
            <line x2="176" y1="-80" y2="-32" x1="176" />
            <line x2="96" y1="-32" y2="-32" x1="0" />
            <line x2="256" y1="-128" y2="-128" x1="320" />
            <line x2="96" y1="-96" y2="-96" x1="0" />
            <line x2="96" y1="-160" y2="-160" x1="0" />
        </blockdef>
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
        <block symbolname="m2_1" name="XLXI_97(7:0)">
            <blockpin signalname="D0(7:0)" name="D0" />
            <blockpin signalname="D1(7:0)" name="D1" />
            <blockpin signalname="S_0(7:0)" name="S0" />
            <blockpin signalname="O(7:0)" name="O" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="S_0(7:0)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="2192" y="1792" type="branch" />
            <wire x2="1600" y1="544" y2="544" x1="928" />
            <wire x2="928" y1="544" y2="1792" x1="928" />
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
        <branch name="D0(7:0)">
            <wire x2="1600" y1="416" y2="416" x1="736" />
        </branch>
        <branch name="D1(7:0)">
            <wire x2="1600" y1="480" y2="480" x1="736" />
        </branch>
        <iomarker fontsize="28" x="2720" y="2368" name="S0" orien="R0" />
        <iomarker fontsize="28" x="736" y="416" name="D0(7:0)" orien="R180" />
        <iomarker fontsize="28" x="736" y="480" name="D1(7:0)" orien="R180" />
        <instance x="1600" y="576" name="XLXI_97(7:0)" orien="R0" />
        <branch name="O(7:0)">
            <wire x2="2704" y1="448" y2="448" x1="1920" />
        </branch>
        <iomarker fontsize="28" x="2704" y="448" name="O(7:0)" orien="R0" />
    </sheet>
</drawing>