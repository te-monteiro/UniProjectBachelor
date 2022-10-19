<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="IN_Z(7:0)" />
        <signal name="IN_Z(6)" />
        <signal name="IN_Z(7)" />
        <signal name="IN_Z(5)" />
        <signal name="IN_Z(4)" />
        <signal name="IN_Z(3)" />
        <signal name="IN_Z(2)" />
        <signal name="IN_Z(1)" />
        <signal name="IN_Z(0)" />
        <signal name="OUT_Z" />
        <signal name="XLXN_16" />
        <signal name="XLXN_17" />
        <signal name="XLXN_18" />
        <signal name="XLXN_19" />
        <signal name="XLXN_20" />
        <signal name="XLXN_21" />
        <signal name="XLXN_22" />
        <signal name="XLXN_24" />
        <port polarity="Input" name="IN_Z(7:0)" />
        <port polarity="Output" name="OUT_Z" />
        <blockdef name="and8">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-512" x1="64" />
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="64" y1="-256" y2="-256" x1="0" />
            <line x2="64" y1="-320" y2="-320" x1="0" />
            <line x2="64" y1="-384" y2="-384" x1="0" />
            <line x2="64" y1="-448" y2="-448" x1="0" />
            <line x2="64" y1="-512" y2="-512" x1="0" />
            <line x2="144" y1="-336" y2="-336" x1="64" />
            <line x2="64" y1="-240" y2="-240" x1="144" />
            <arc ex="144" ey="-336" sx="144" sy="-240" r="48" cx="144" cy="-288" />
            <line x2="192" y1="-288" y2="-288" x1="256" />
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
        <block symbolname="and8" name="XLXI_1">
            <blockpin signalname="XLXN_24" name="I0" />
            <blockpin signalname="XLXN_22" name="I1" />
            <blockpin signalname="XLXN_21" name="I2" />
            <blockpin signalname="XLXN_20" name="I3" />
            <blockpin signalname="XLXN_19" name="I4" />
            <blockpin signalname="XLXN_18" name="I5" />
            <blockpin signalname="XLXN_17" name="I6" />
            <blockpin signalname="XLXN_16" name="I7" />
            <blockpin signalname="OUT_Z" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_5">
            <blockpin signalname="IN_Z(7)" name="I" />
            <blockpin signalname="XLXN_16" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_6">
            <blockpin signalname="IN_Z(6)" name="I" />
            <blockpin signalname="XLXN_17" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_7">
            <blockpin signalname="IN_Z(5)" name="I" />
            <blockpin signalname="XLXN_18" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_8">
            <blockpin signalname="IN_Z(4)" name="I" />
            <blockpin signalname="XLXN_19" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_9">
            <blockpin signalname="IN_Z(3)" name="I" />
            <blockpin signalname="XLXN_20" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_10">
            <blockpin signalname="IN_Z(2)" name="I" />
            <blockpin signalname="XLXN_21" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_11">
            <blockpin signalname="IN_Z(1)" name="I" />
            <blockpin signalname="XLXN_22" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_12">
            <blockpin signalname="IN_Z(0)" name="I" />
            <blockpin signalname="XLXN_24" name="O" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="IN_Z(7:0)">
            <wire x2="816" y1="608" y2="1056" x1="816" />
            <wire x2="816" y1="1056" y2="1120" x1="816" />
            <wire x2="816" y1="1120" y2="1184" x1="816" />
            <wire x2="816" y1="1184" y2="1248" x1="816" />
            <wire x2="816" y1="1248" y2="1312" x1="816" />
            <wire x2="816" y1="1312" y2="1376" x1="816" />
            <wire x2="816" y1="1376" y2="1440" x1="816" />
            <wire x2="816" y1="1440" y2="1504" x1="816" />
            <wire x2="816" y1="1504" y2="2080" x1="816" />
        </branch>
        <iomarker fontsize="28" x="816" y="608" name="IN_Z(7:0)" orien="R270" />
        <bustap x2="912" y1="1120" y2="1120" x1="816" />
        <branch name="IN_Z(6)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1232" y="1120" type="branch" />
            <wire x2="1232" y1="1120" y2="1120" x1="912" />
            <wire x2="1664" y1="1120" y2="1120" x1="1232" />
        </branch>
        <bustap x2="912" y1="1056" y2="1056" x1="816" />
        <branch name="IN_Z(7)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1232" y="1056" type="branch" />
            <wire x2="1232" y1="1056" y2="1056" x1="912" />
            <wire x2="1664" y1="1056" y2="1056" x1="1232" />
        </branch>
        <bustap x2="912" y1="1184" y2="1184" x1="816" />
        <branch name="IN_Z(5)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1232" y="1184" type="branch" />
            <wire x2="1232" y1="1184" y2="1184" x1="912" />
            <wire x2="1664" y1="1184" y2="1184" x1="1232" />
        </branch>
        <bustap x2="912" y1="1248" y2="1248" x1="816" />
        <branch name="IN_Z(4)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1232" y="1248" type="branch" />
            <wire x2="1232" y1="1248" y2="1248" x1="912" />
            <wire x2="1664" y1="1248" y2="1248" x1="1232" />
        </branch>
        <bustap x2="912" y1="1312" y2="1312" x1="816" />
        <branch name="IN_Z(3)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1232" y="1312" type="branch" />
            <wire x2="1232" y1="1312" y2="1312" x1="912" />
            <wire x2="1664" y1="1312" y2="1312" x1="1232" />
        </branch>
        <bustap x2="912" y1="1376" y2="1376" x1="816" />
        <branch name="IN_Z(2)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1232" y="1376" type="branch" />
            <wire x2="1232" y1="1376" y2="1376" x1="912" />
            <wire x2="1664" y1="1376" y2="1376" x1="1232" />
        </branch>
        <bustap x2="912" y1="1440" y2="1440" x1="816" />
        <branch name="IN_Z(1)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1232" y="1440" type="branch" />
            <wire x2="1232" y1="1440" y2="1440" x1="912" />
            <wire x2="1664" y1="1440" y2="1440" x1="1232" />
        </branch>
        <bustap x2="912" y1="1504" y2="1504" x1="816" />
        <branch name="IN_Z(0)">
            <attrtext style="alignment:SOFT-BCENTER;fontsize:28;fontname:Arial" attrname="Name" x="1232" y="1504" type="branch" />
            <wire x2="1232" y1="1504" y2="1504" x1="912" />
            <wire x2="1664" y1="1504" y2="1504" x1="1232" />
        </branch>
        <branch name="OUT_Z">
            <wire x2="2976" y1="1280" y2="1280" x1="2736" />
        </branch>
        <instance x="2480" y="1568" name="XLXI_1" orien="R0" />
        <iomarker fontsize="28" x="2976" y="1280" name="OUT_Z" orien="R0" />
        <instance x="1664" y="1088" name="XLXI_5" orien="R0" />
        <instance x="1664" y="1152" name="XLXI_6" orien="R0" />
        <instance x="1664" y="1216" name="XLXI_7" orien="R0" />
        <instance x="1664" y="1280" name="XLXI_8" orien="R0" />
        <instance x="1664" y="1344" name="XLXI_9" orien="R0" />
        <instance x="1664" y="1408" name="XLXI_10" orien="R0" />
        <instance x="1664" y="1472" name="XLXI_11" orien="R0" />
        <instance x="1664" y="1536" name="XLXI_12" orien="R0" />
        <branch name="XLXN_16">
            <wire x2="2480" y1="1056" y2="1056" x1="1888" />
        </branch>
        <branch name="XLXN_17">
            <wire x2="2480" y1="1120" y2="1120" x1="1888" />
        </branch>
        <branch name="XLXN_18">
            <wire x2="2480" y1="1184" y2="1184" x1="1888" />
        </branch>
        <branch name="XLXN_19">
            <wire x2="2480" y1="1248" y2="1248" x1="1888" />
        </branch>
        <branch name="XLXN_20">
            <wire x2="2480" y1="1312" y2="1312" x1="1888" />
        </branch>
        <branch name="XLXN_21">
            <wire x2="2480" y1="1376" y2="1376" x1="1888" />
        </branch>
        <branch name="XLXN_22">
            <wire x2="2480" y1="1440" y2="1440" x1="1888" />
        </branch>
        <branch name="XLXN_24">
            <wire x2="2480" y1="1504" y2="1504" x1="1888" />
        </branch>
    </sheet>
</drawing>