interface  PageDocumentationCssSettings {
  "width": string,
  "margin-left": string
};

export function cssSettings(width : number, margin_left : number = 25) : PageDocumentationCssSettings {return { "width": ""+width+"%", "margin-left": ""+margin_left+"%" }; }