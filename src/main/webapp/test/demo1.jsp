<%--
  Created by IntelliJ IDEA.
  User: longfei
  Date: 16-4-6
  Time: 下午10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        @import "/dojoroot/dijit/themes/soria/soria.css";
        @import "/dojoroot/dojo/resources/dojo.css";
        @import "/dojoroot/dojo/resources/dnd.css";
        @import "/dojoroot/dojox/widget/Portlet/Portlet.css";
        @import "/dojoroot/dojox/layout/resources/GridContainer.css";
    </style>
    <script>

    </script>
    <script type="text/javascript" src="/dojoroot/dojo/dojo.js" data-dojo-config="paseOnLoad:true"></script>
    <script type="text/javascript">

        require(["dijit/layout/AccordionContainer", "dijit/layout/ContentPane", "dojo/domReady!"],
                function(AccordionContainer, ContentPane){
                    var aContainer = new AccordionContainer({style:"height: 300px"}, "markup");
                    aContainer.addChild(new ContentPane({
                        title: "This is a content pane",
                        content: "Hi!"
                    }));
                    aContainer.addChild(new ContentPane({
                        title:"This is as well",
                        content:"Hi how are you?"
                    }));
                    aContainer.addChild(new ContentPane({
                        title:"This too",
                        content:"Hello im fine.. thnx"
                    }));
                    aContainer.startup();
                });

        require([
            "dijit/layout/BorderContainer", "dijit/layout/ContentPane",
            "dojo/domReady!"
        ], function(BorderContainer, ContentPane){
            // create a BorderContainer as the top widget in the hierarchy
            var bc = new BorderContainer({
                style: "height: 300px; width: 1500px;"
            });

            // create a ContentPane as the left pane in the BorderContainer
            var cp = new ContentPane({
                region: "top",
                style: "width: 100%",
                content: "hello world"
            });
            bc.addChild(cp);
            var cp1 = new ContentPane({
                region: "left",
                style: "width: 100px",
                content: "hello world"
            });
            bc.addChild(cp1);

            // create a ContentPane as the center pane in the BorderContainer
            var cp2 = new ContentPane({
                region: "center",
                content: "how are you?"
            });
            bc.addChild(cp2);

            // put the top level widget into the document, and then call startup()
            bc.placeAt(document.body);
            bc.startup();
        });

        require([
            "dijit/layout/StackContainer",
            "dijit/layout/ContentPane",
            "dijit/layout/StackController",
            "dojo/domReady!"
        ], function(StackContainer, ContentPane, StackController){
            var sc = new StackContainer({
                style: "height: 300px; width: 400px;",
                id: "myProgStackContainer"
            }, "scontainer-prog");

            var cp1 = new ContentPane({
                title: "page 1",
                content: "page 1 content"
            });
            sc.addChild(cp1);

            var cp2 = new ContentPane({
                title: "page 2",
                content: "page 2 content"
            });
            sc.addChild(cp2);

            var controller = new StackController({containerId: "myProgStackContainer"}, "scontroller-prog");

            sc.startup();
            controller.startup();
        });

        require(["dijit/layout/TabContainer", "dijit/layout/ContentPane", "dojo/domReady!"], function(TabContainer, ContentPane){
            var tc = new TabContainer({
                style: "height: 100%; width: 100%;"
            }, "tc1-prog");

            var cp1 = new ContentPane({
                title: "Food",
                content: "We offer amazing food"
            });
            tc.addChild(cp1);

            var cp2 = new ContentPane({
                title: "Drinks",
                content: "We are known for our drinks."
            });
            tc.addChild(cp2);

            tc.startup();
        });

        require(["dijit/registry", "dojox/layout/GridContainer", "dojox/widget/Portlet",
                    "dijit/form/DropDownButton", "dijit/TooltipDialog", "dojo/dom-construct", "dojo/domReady!"]
                , function(registry, GridContainer, Portlet, DropDownButton, TooltipDialog, domConstruct){
                    // create a new GridContainer:
                    var gridContainer = new GridContainer({
                        nbZones: 3,
                        opacity: .5,
                        hasResizableColumns: false,
                        allowAutoScroll: false,
                        withHandles: true,
                        dragHandleClass: 'dijitTitlePaneTitle',
                        style: {width:'100%'},
                        acceptTypes: ['Portlet'],
                        isOffset: true
                    }, 'placeHere');
                    // prepare some Content for the Portlet:
                    var portletContent1 = [
                        domConstruct.create('div', {innerHTML: 'Some content within the Portlet "dynPortlet1".'})
                    ];
                    // create a new Portlet:
                    var portlet1 = Portlet({
                        id: 'dynPortlet1',
                        closable: false,
                        dndType: 'Portlet',
                        title: 'Portlet "dynPortlet1"',
                        content: portletContent1
                    });
                    // create a new TooltipDialog:
                    var tooltipDialog=new TooltipDialog({
                        content: "Content for TooltipDialog",
                        style: {width:'320px'}
                    });
                    // create a new DropDownButton and assign the TooltipDialog:
                    var testButton = new DropDownButton({
                        label: "Click me",
                        dropDown: tooltipDialog
                    });
                    // add the DropDownButton to the Portlet:
                    portlet1.addChild(testButton);
                    // add the first Portlet to the GridContainer:
                    gridContainer.addChild(portlet1);
                    // startup GridContainer:
                    gridContainer.startup();
                });
        require(["dojox/layout/ScrollPane"], function( ScrollPane){
            var scrollPane = new dojox.layout.ScrollPane({
                orientation: "vertical",
                style:"width:125px; height:200px; border:1px solid; overflow:hidden;"
            },"someNodeId");
        });
        require(["dijit/dijit","dojox/layout/TableContainer","dijit/form/TextBox",
            "dijit/form/CheckBox","dijit/form/NumberSpinner"],function (TableContainer,TextBox,CheckBox,NumberSpinner) {
            var programmatic = new TableContainer(
                    {
                        cols: 2,
                        customClass:"labelsAndValues",
                        "labelWidth": "150"
                    }, dojo.byId("putWidgetHere"));

// Create four text boxes
            var text1 = new TextBox({label: "ProgText 1"});
            var text2 = new TextBox({label: "ProgText 2"});
            var text3 = new TextBox({label: "ProgText 3"});
            var text4 = new TextBox({label: "ProgText 4"});

// Add the four text boxes to the TableContainer
            programmatic.addChild(text1);
            programmatic.addChild(text2);
            programmatic.addChild(text3);
            programmatic.addChild(text4);

// Start the table container. This initializes it and places
// the child widgets in the correct place.
            programmatic.startup();
        })
      
    </script>
    <title>Title</title>
</head>
<body class="soria">
    <div id="markup" style="width: 300px; height: 300px"></div>
    <div id="scontainer-prog"></div>
    <div id="scontroller-prog"></div>
    <div style="width: 350px; height: 290px">
        <div id="tc1-prog"></div>
    </div>
    <div id='placeHere'>&nbsp;</div>
    <div id='someNodeId'><div id="someNodeId0">
        <ol>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
            <li>text</li>
        </ol>
    </div></div>
    <div id="putWidgetHere" >dsad</div>
</body>
</html>
