function ajaxSuccess (xhr,clbck) {
 if (xhr.readyState==4){
  clbck(xhr.responseText)
 // console.log(this.responseText);
}
}
const formToJSON = elements => [].reduce.call(elements, (data, element) => {
 if ( element.type!="submit" && element.type!="button"){   
  data[element.id] = element.value;
 }
 
return data;
}, {});
function postCallback(xmlhttp)
{
if (xmlhttp.status===200){

  console.log(this.responseText);
}
}

function JsonSubmit (oFormElement,clbck) {
  if (!oFormElement.action) { return; }
  var oReq = new XMLHttpRequest();
  
  oReq.onload = ajaxSuccess(oReq,clbck);
 oReq.onreadystatechange=function () { ajaxSuccess(oReq,clbck); };

  if (oFormElement.method.toLowerCase() === "post") {
	var json=formToJSON(oFormElement.elements);
        oReq.open("post", oFormElement.action,true);
	oReq.setRequestHeader("Content-Type", "application/json");
  	oReq.send(JSON.stringify(json));
  } else {
    var oField, sFieldType, nFile, sSearch = "";
    for (var nItem = 0; nItem < oFormElement.elements.length; nItem++) {
      oField = oFormElement.elements[nItem];
      if (!oField.hasAttribute("name")) { continue; }
      sFieldType = oField.nodeName.toUpperCase() === "INPUT" ?
          oField.getAttribute("type").toUpperCase() : "TEXT";
      if (sFieldType === "FILE") {
        for (nFile = 0; nFile < oField.files.length;
            sSearch += "&" + escape(oField.name) + "=" + escape(oField.files[nFile++].name));
      } else if ((sFieldType !== "RADIO" && sFieldType !== "CHECKBOX") || oField.checked) {
        sSearch += "&" + escape(oField.name) + "=" + escape(oField.value);
      }
    }
    if(!oFormElement.action.includes("?")){
	oReq.open("get", oFormElement.action.replace(/(?:\?.*)?$/, sSearch.replace(/^&/, "?")), true);
	}else{
	oReq.open("get", oFormElement.action, true);	
	}
	//oReq.setRequestHeader('Accept', 'application/json');
    oReq.send(null);
  }
}
