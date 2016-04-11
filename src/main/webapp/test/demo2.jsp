<%--
  Created by IntelliJ IDEA.
  User: longfei
  Date: 16-4-7
  Time: 上午12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
        require([
            "dijit/layout/BorderContainer", "dijit/layout/ContentPane","dijit/MenuBar",
            "dijit/PopupMenuBarItem",
            "dijit/Menu",
            "dijit/MenuItem",
            "dijit/DropDownMenu",
            "dojo/domReady!"
        ], function(BorderContainer, ContentPane,MenuBar, PopupMenuBarItem, Menu, MenuItem, DropDownMenu){
            var pMenuBar = new MenuBar({});

            var pSubMenu = new DropDownMenu({});
            pSubMenu.addChild(new MenuItem({
                label: "File item #1"
            }));
            pSubMenu.addChild(new MenuItem({
                label: "File item #2"
            }));
            pMenuBar.addChild(new PopupMenuBarItem({
                label: "File",
                popup: pSubMenu
            }));

            var pSubMenu2 = new DropDownMenu({});
            pSubMenu2.addChild(new MenuItem({
                label: "Cut",
                iconClass: "dijitEditorIcon dijitEditorIconCut"
            }));
            pSubMenu2.addChild(new MenuItem({
                label: "Copy",
                iconClass: "dijitEditorIcon dijitEditorIconCopy"
            }));
            pSubMenu2.addChild(new MenuItem({
                label: "Paste",
                iconClass: "dijitEditorIcon dijitEditorIconPaste"
            }));
            pMenuBar.addChild(new PopupMenuBarItem({
                label: "Edit",
                popup: pSubMenu2
            }));


            // create a BorderContainer as the top widget in the hierarchy
            var bc = new BorderContainer({
                style: "height: 100%; width: 100%;"
            });

            // create a ContentPane as the left pane in the BorderContainer
            var cp = new ContentPane({
                region: "top",
                style: "width: 100%",
            });
            cp.addChild(pMenuBar);
            bc.addChild(cp);
            var cp1 = new ContentPane({
                region: "left",
                style: "width: 100px",
                content: "hello world2"
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

    </script>
</head>
<body class="soria">

</body>
</html>
