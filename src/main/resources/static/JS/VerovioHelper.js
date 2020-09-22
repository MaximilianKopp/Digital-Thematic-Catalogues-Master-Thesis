function addInputLine() {
    var node = document.createElement("input");                 // Create an <input> node
    document.getElementById("parentElement").appendChild(node);     // Append it to the parent
}

function meiExport() {
    var vrvToolkit = new verovio.toolkit();
    vrvToolkit.load


}

$.ajax({
    type: "GET",
    contentType: "application/json",
    url: "http://localhost:8080/test/mei",
    dataType: "json",
    success: function (data, status) {

    },
    error: function (status) {

    }
});


