import SwiftUI
import shared

struct SearchScreen: View {
    @StateObject var viewModel = SearchScreenViewModel()
        
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)
    
    var body: some View {
        NavigationStack{
            
            ScrollView{
                LazyVGrid(columns: gridColumns, spacing: 16){
                    
                    ForEach(viewModel.products, id: \.id) { product in
                        
                        ProductGridItem(product: product)
                            .buttonStyle(PlainButtonStyle())
                    }
                    
                    if viewModel.isLoading {
                        Section(footer: ProgressView()){}
                    }
                    
                }
                .padding(.horizontal, 12)
                .navigationDestination(for: ProductItem.self) { product in
                    DetailScreen(productId: product.id)
                }
            }
        }
        .task {
            await viewModel.loadMovies()
        }
	}
}

struct SearchScreen_Previews: PreviewProvider {
	static var previews: some View {
		SearchScreen()
	}
}
