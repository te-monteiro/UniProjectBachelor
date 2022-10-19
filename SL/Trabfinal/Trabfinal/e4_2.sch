<?xml version="1.0" encoding="UTF-8"?>
<drawing version="7">
    <attr value="spartan3e" name="DeviceFamilyName">
        <trait delete="all:0" />
        <trait editname="all:0" />
        <trait edittrait="all:0" />
    </attr>
    <netlist>
        <signal name="XLXN_7" />
        <signal name="XLXN_10" />
        <signal name="XLXN_25" />
        <signal name="XLXN_27" />
        <signal name="XLXN_29" />
        <signal name="XLXN_31" />
        <signal name="XLXN_33" />
        <signal name="XLXN_34" />
        <signal name="XLXN_36" />
        <signal name="XLXN_37" />
        <signal name="XLXN_3" />
        <signal name="XLXN_4" />
        <signal name="XLXN_5" />
        <signal name="D3" />
        <signal name="D2" />
        <signal name="D1" />
        <signal name="D0" />
        <signal name="XLXN_79" />
        <signal name="XLXN_52" />
        <signal name="XLXN_81" />
        <signal name="XLXN_51" />
        <signal name="XLXN_83" />
        <signal name="XLXN_50" />
        <signal name="XLXN_88" />
        <signal name="XLXN_90" />
        <signal name="XLXN_89" />
        <signal name="XLXN_98" />
        <signal name="XLXN_99" />
        <signal name="XLXN_100" />
        <signal name="Q1" />
        <signal name="Q0" />
        <port polarity="Input" name="D3" />
        <port polarity="Input" name="D2" />
        <port polarity="Input" name="D1" />
        <port polarity="Input" name="D0" />
        <port polarity="Output" name="Q1" />
        <port polarity="Output" name="Q0" />
        <blockdef name="inv">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-32" y2="-32" x1="0" />
            <line x2="160" y1="-32" y2="-32" x1="224" />
            <line x2="128" y1="-64" y2="-32" x1="64" />
            <line x2="64" y1="-32" y2="0" x1="128" />
            <line x2="64" y1="0" y2="-64" x1="64" />
            <circle r="16" cx="144" cy="-32" />
        </blockdef>
        <blockdef name="nand3">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="216" y1="-128" y2="-128" x1="256" />
            <circle r="12" cx="204" cy="-128" />
            <line x2="144" y1="-176" y2="-176" x1="64" />
            <line x2="64" y1="-80" y2="-80" x1="144" />
            <arc ex="144" ey="-176" sx="144" sy="-80" r="48" cx="144" cy="-128" />
            <line x2="64" y1="-64" y2="-192" x1="64" />
        </blockdef>
        <blockdef name="nand4">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="64" y1="-192" y2="-192" x1="0" />
            <line x2="64" y1="-256" y2="-256" x1="0" />
            <line x2="216" y1="-160" y2="-160" x1="256" />
            <circle r="12" cx="204" cy="-160" />
            <line x2="64" y1="-64" y2="-256" x1="64" />
            <line x2="144" y1="-208" y2="-208" x1="64" />
            <arc ex="144" ey="-208" sx="144" sy="-112" r="48" cx="144" cy="-160" />
            <line x2="64" y1="-112" y2="-112" x1="144" />
        </blockdef>
        <blockdef name="nand2">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="216" y1="-96" y2="-96" x1="256" />
            <circle r="12" cx="204" cy="-96" />
            <line x2="64" y1="-48" y2="-144" x1="64" />
            <line x2="144" y1="-144" y2="-144" x1="64" />
            <line x2="64" y1="-48" y2="-48" x1="144" />
            <arc ex="144" ey="-144" sx="144" sy="-48" r="48" cx="144" cy="-96" />
        </blockdef>
        <blockdef name="or2">
            <timestamp>2000-1-1T10:10:10</timestamp>
            <line x2="64" y1="-64" y2="-64" x1="0" />
            <line x2="64" y1="-128" y2="-128" x1="0" />
            <line x2="192" y1="-96" y2="-96" x1="256" />
            <arc ex="192" ey="-96" sx="112" sy="-48" r="88" cx="116" cy="-136" />
            <arc ex="48" ey="-144" sx="48" sy="-48" r="56" cx="16" cy="-96" />
            <line x2="48" y1="-144" y2="-144" x1="112" />
            <arc ex="112" ey="-144" sx="192" sy="-96" r="88" cx="116" cy="-56" />
            <line x2="48" y1="-48" y2="-48" x1="112" />
        </blockdef>
        <block symbolname="inv" name="XLXI_1">
            <blockpin signalname="D3" name="I" />
            <blockpin signalname="XLXN_50" name="O" />
        </block>
        <block symbolname="nand3" name="XLXI_2">
            <blockpin signalname="D1" name="I0" />
            <blockpin signalname="XLXN_50" name="I1" />
            <blockpin signalname="XLXN_51" name="I2" />
            <blockpin signalname="XLXN_52" name="O" />
        </block>
        <block symbolname="nand4" name="XLXI_3">
            <blockpin signalname="D0" name="I0" />
            <blockpin signalname="XLXN_50" name="I1" />
            <blockpin signalname="XLXN_51" name="I2" />
            <blockpin signalname="XLXN_52" name="I3" />
            <blockpin signalname="XLXN_89" name="O" />
        </block>
        <block symbolname="nand2" name="XLXI_16">
            <blockpin signalname="D2" name="I0" />
            <blockpin signalname="XLXN_50" name="I1" />
            <blockpin signalname="XLXN_51" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_6">
            <blockpin signalname="XLXN_52" name="I" />
            <blockpin signalname="XLXN_98" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_7">
            <blockpin signalname="XLXN_50" name="I" />
            <blockpin signalname="XLXN_100" name="O" />
        </block>
        <block symbolname="inv" name="XLXI_17">
            <blockpin signalname="XLXN_51" name="I" />
            <blockpin signalname="XLXN_99" name="O" />
        </block>
        <block symbolname="or2" name="XLXI_41">
            <blockpin signalname="XLXN_99" name="I0" />
            <blockpin signalname="XLXN_100" name="I1" />
            <blockpin signalname="Q1" name="O" />
        </block>
        <block symbolname="or2" name="XLXI_42">
            <blockpin signalname="XLXN_98" name="I0" />
            <blockpin signalname="XLXN_100" name="I1" />
            <blockpin signalname="Q0" name="O" />
        </block>
    </netlist>
    <sheet sheetnum="1" width="3520" height="2720">
        <branch name="D3">
            <wire x2="1424" y1="800" y2="800" x1="880" />
        </branch>
        <branch name="D2">
            <wire x2="1424" y1="1088" y2="1088" x1="880" />
        </branch>
        <branch name="D1">
            <wire x2="1424" y1="1440" y2="1440" x1="880" />
        </branch>
        <branch name="D0">
            <wire x2="1424" y1="1808" y2="1808" x1="880" />
        </branch>
        <branch name="XLXN_52">
            <wire x2="1088" y1="1536" y2="1616" x1="1088" />
            <wire x2="1424" y1="1616" y2="1616" x1="1088" />
            <wire x2="1760" y1="1536" y2="1536" x1="1088" />
            <wire x2="1760" y1="1376" y2="1376" x1="1680" />
            <wire x2="1760" y1="1376" y2="1536" x1="1760" />
            <wire x2="1888" y1="1376" y2="1376" x1="1760" />
        </branch>
        <branch name="XLXN_51">
            <wire x2="1040" y1="1200" y2="1312" x1="1040" />
            <wire x2="1424" y1="1312" y2="1312" x1="1040" />
            <wire x2="1040" y1="1312" y2="1680" x1="1040" />
            <wire x2="1424" y1="1680" y2="1680" x1="1040" />
            <wire x2="1760" y1="1200" y2="1200" x1="1040" />
            <wire x2="1760" y1="1056" y2="1056" x1="1680" />
            <wire x2="1760" y1="1056" y2="1200" x1="1760" />
            <wire x2="1888" y1="1056" y2="1056" x1="1760" />
        </branch>
        <branch name="XLXN_50">
            <wire x2="992" y1="944" y2="1024" x1="992" />
            <wire x2="1424" y1="1024" y2="1024" x1="992" />
            <wire x2="992" y1="1024" y2="1376" x1="992" />
            <wire x2="1424" y1="1376" y2="1376" x1="992" />
            <wire x2="992" y1="1376" y2="1744" x1="992" />
            <wire x2="1424" y1="1744" y2="1744" x1="992" />
            <wire x2="1760" y1="944" y2="944" x1="992" />
            <wire x2="1760" y1="800" y2="800" x1="1648" />
            <wire x2="1760" y1="800" y2="944" x1="1760" />
            <wire x2="1888" y1="800" y2="800" x1="1760" />
        </branch>
        <instance x="1424" y="832" name="XLXI_1" orien="R0" />
        <instance x="1424" y="1504" name="XLXI_2" orien="R0" />
        <instance x="1424" y="1872" name="XLXI_3" orien="R0" />
        <instance x="1424" y="1152" name="XLXI_16" orien="R0" />
        <iomarker fontsize="28" x="880" y="1808" name="D0" orien="R180" />
        <iomarker fontsize="28" x="880" y="1440" name="D1" orien="R180" />
        <iomarker fontsize="28" x="880" y="1088" name="D2" orien="R180" />
        <iomarker fontsize="28" x="880" y="800" name="D3" orien="R180" />
        <instance x="1888" y="1408" name="XLXI_6" orien="R0" />
        <instance x="1888" y="832" name="XLXI_7" orien="R0" />
        <instance x="1888" y="1088" name="XLXI_17" orien="R0" />
        <branch name="XLXN_89">
            <wire x2="1088" y1="1904" y2="1984" x1="1088" />
            <wire x2="1440" y1="1984" y2="1984" x1="1088" />
            <wire x2="1760" y1="1904" y2="1904" x1="1088" />
            <wire x2="1760" y1="1712" y2="1712" x1="1680" />
            <wire x2="1760" y1="1712" y2="1904" x1="1760" />
        </branch>
        <branch name="XLXN_98">
            <wire x2="2336" y1="1376" y2="1376" x1="2112" />
        </branch>
        <instance x="2336" y="928" name="XLXI_41" orien="R0" />
        <branch name="XLXN_99">
            <wire x2="2272" y1="1056" y2="1056" x1="2112" />
            <wire x2="2336" y1="864" y2="864" x1="2272" />
            <wire x2="2272" y1="864" y2="1056" x1="2272" />
        </branch>
        <instance x="2336" y="1440" name="XLXI_42" orien="R0" />
        <branch name="XLXN_100">
            <wire x2="2160" y1="800" y2="800" x1="2112" />
            <wire x2="2336" y1="800" y2="800" x1="2160" />
            <wire x2="2160" y1="800" y2="1312" x1="2160" />
            <wire x2="2336" y1="1312" y2="1312" x1="2160" />
        </branch>
        <branch name="Q1">
            <wire x2="2624" y1="832" y2="832" x1="2592" />
        </branch>
        <iomarker fontsize="28" x="2624" y="832" name="Q1" orien="R0" />
        <branch name="Q0">
            <wire x2="2624" y1="1344" y2="1344" x1="2592" />
        </branch>
        <iomarker fontsize="28" x="2624" y="1344" name="Q0" orien="R0" />
    </sheet>
</drawing>