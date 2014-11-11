class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/"(controller: 'home')
		"/"(view:"/index")
        "/help"(controller:"fileDownload", action: "help")
        "/$action"(controller:"staticView")
		"500"(view:'/error')
	}
}
