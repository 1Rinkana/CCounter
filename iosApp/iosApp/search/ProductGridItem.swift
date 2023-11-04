import SwiftUI
import shared

struct ProductGridItem: View {
    let product: ProductItem
    
    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            ZStack {
                Color(.white)
                
                AsyncImage(url: URL(string: product.imageUrl)) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } placeholder: {
                    Color.white
                }
            }
            .overlay(
                RoundedRectangle(cornerRadius: 16)
                    .stroke(Color.gray, lineWidth: 1)
            )
            .frame(height: 175)
            .clipShape(RoundedRectangle(cornerRadius: 16.0))
            
            Text(product.title)
                .fontWeight(.regular)
                .lineLimit(1)
                .padding(.bottom, 4)
                .padding(.vertical, 6)
        }
        .clipShape(RoundedRectangle(cornerRadius: 16.0))
    }
}
